package br.com.ptck.app.core.usecase;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import br.com.ptck.app.core.Produto;
import br.com.ptck.app.core.Produto.CategoriaEnum;
import br.com.ptck.app.core.exception.NotFoundCategoriaException;
import br.com.ptck.app.core.exception.NotFoundProdutoException;
import br.com.ptck.app.core.gateway.ProdutoRepository;
import br.com.ptck.app.core.tarifas.ProdutoAutomovel;
import br.com.ptck.app.core.tarifas.ProdutoPatrimonial;
import br.com.ptck.app.core.tarifas.ProdutoResidencial;
import br.com.ptck.app.core.tarifas.ProdutoViagem;
import br.com.ptck.app.core.tarifas.ProdutoVida;
import br.com.ptck.app.presenter.rest.entities.ProdutoResponse;
import lombok.Value;

public class UpdateProdutoUseCase implements UseCase<UpdateProdutoUseCase.InputValues, UpdateProdutoUseCase.OutputValues> {

    private GetProdutoByIdUseCase getProdutoByIdUseCase;
    private ProdutoRepository repository;
    
    public UpdateProdutoUseCase(ProdutoRepository repository, 
                                GetProdutoByIdUseCase getProdutoByIdUseCase) {
        this.repository = repository;
        this.getProdutoByIdUseCase = getProdutoByIdUseCase;
    }

    @Override
    public OutputValues execute(InputValues request) {
        Produto produto = update(request);

        Produto update = repository.persist(produto);

        return new OutputValues(ProdutoResponse.from(update));
    }

    private Produto update(InputValues request) {

        CategoriaEnum categoria = verificarCategoriaExistente(request.getCategoria());

        Optional<Produto> optional = getProduto(request);

        if(optional.isEmpty())
            throw new NotFoundProdutoException("Produto não encontrado");

        Produto updateProduto = optional.get();

        updateProduto.setId(updateProduto.getId());
        updateProduto.setCategoria(categoria);
        updateProduto.setNome(request.getNome());
        updateProduto.setPrecoBase(toBigDecimal(request.getPrecoBase()));
        updateProduto.setPrecoTarifado(calcular(updateProduto));

        return updateProduto;
    }

    private Optional<Produto> getProduto(InputValues input) {
        GetProdutoByIdUseCase.InputValues inputValues = new GetProdutoByIdUseCase.InputValues(convertToUUID(input.getId()));
        GetProdutoByIdUseCase.OutputValues execute = getProdutoByIdUseCase.execute(inputValues);

        return execute.getOptionalProduto();
    }

    private CategoriaEnum verificarCategoriaExistente(String categoria) {
        Optional<CategoriaEnum> categorias = CategoriaEnum.convertToEnum(categoria);

        if (categorias.isEmpty())
            throw new NotFoundCategoriaException("Categoria não encontrada");

        return categorias.get();
    }

    private BigDecimal toBigDecimal(Double valor){
        return new BigDecimal(valor).setScale(2, RoundingMode.HALF_DOWN);
    }

    private BigDecimal calcular(Produto produto) {
        return calcularTarifa(produto.getPrecoBase(), produto.getCategoria());
    }

    public UUID convertToUUID(String uuid){
        try {
            return UUID.fromString(uuid);
        } catch (IllegalArgumentException e) {
            throw new NotFoundProdutoException("Formato do ID incorreto");
        }
    }

    private BigDecimal calcularTarifa(BigDecimal precoBase, CategoriaEnum categoria) {
        switch (categoria) {
            case VIDA:
                return new ProdutoVida().calcularTarifa(precoBase);
            case AUTO:
                return new ProdutoAutomovel().calcularTarifa(precoBase);
            case PATRIMONIAL:
                return new ProdutoPatrimonial().calcularTarifa(precoBase);
            case RESIDENCIAL:
                return new ProdutoResidencial().calcularTarifa(precoBase);
            case VIAGEM:
                return new ProdutoViagem().calcularTarifa(precoBase);
        }
        return null;
    }

    @Value
    public static class InputValues implements UseCase.InputValues {
        private final String id;
        private final String nome;
        private final String categoria;
        private final Double precoBase;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        private final ProdutoResponse produto;
    }

}
