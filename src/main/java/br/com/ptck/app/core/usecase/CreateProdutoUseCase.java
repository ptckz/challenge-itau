package br.com.ptck.app.core.usecase;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

import br.com.ptck.app.core.Produto;
import br.com.ptck.app.core.Produto.CategoriaEnum;
import br.com.ptck.app.core.exception.NotFoundCategoriaException;
import br.com.ptck.app.core.gateway.ProdutoRepository;
import br.com.ptck.app.core.tarifas.ProdutoAutomovel;
import br.com.ptck.app.core.tarifas.ProdutoPatrimonial;
import br.com.ptck.app.core.tarifas.ProdutoResidencial;
import br.com.ptck.app.core.tarifas.ProdutoViagem;
import br.com.ptck.app.core.tarifas.ProdutoVida;
import br.com.ptck.app.presenter.rest.entities.ProdutoResponse;
import lombok.Value;

public class CreateProdutoUseCase implements UseCase<CreateProdutoUseCase.InputValues, CreateProdutoUseCase.OutputValues>{

    private ProdutoRepository repository;

    public CreateProdutoUseCase(ProdutoRepository repository) {
        this.repository = repository;
    }

    @Override
    public OutputValues execute(InputValues request) {
        Produto produto = create(request);

        return new OutputValues(ProdutoResponse.from(repository.persist(produto)));
    }

    private Produto create(InputValues input) {
        CategoriaEnum categoria = verificarCategoriaExistente(input.getCategoria());

        Produto createProduto = Produto.newInstance(
                input.getNome(),
                categoria,
                toBigDecimal(input.getPrecoBase()));

        createProduto.setPrecoTarifado(calcular(createProduto));
        return createProduto;
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
        private final String nome;
        private final String categoria;
        private final Double precoBase;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        private final ProdutoResponse produto;
    }

}
