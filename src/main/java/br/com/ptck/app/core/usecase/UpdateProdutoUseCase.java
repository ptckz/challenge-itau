package br.com.ptck.app.core.usecase;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;
import java.util.UUID;

import br.com.ptck.app.core.Produto;
import br.com.ptck.app.core.Produto.CategoriaEnum;
import br.com.ptck.app.core.exception.NotFoundCategoriaException;
import br.com.ptck.app.core.exception.NotFoundProdutoException;
import br.com.ptck.app.core.gateway.ProdutoRepository;
import br.com.ptck.app.presenter.rest.entities.ProdutoRequest;
import br.com.ptck.app.presenter.rest.entities.ProdutoResponse;

public class UpdateProdutoUseCase implements UseCase<ProdutoRequest, ProdutoResponse> {

    private ProdutoRepository repository;
    private CalcularTarifaProdutoUseCase calcularTarifa;
    private FindProdutoByIdUseCase findByProdutoIdUseCase;
    
    public UpdateProdutoUseCase(ProdutoRepository repository, 
                                CalcularTarifaProdutoUseCase calcularTarifa,
                                FindProdutoByIdUseCase findByProdutoIdUseCase) {
        this.repository = repository;
        this.calcularTarifa = calcularTarifa;
        this.findByProdutoIdUseCase = findByProdutoIdUseCase;
    }

    @Override
    public ProdutoResponse execute(ProdutoRequest request, String id) {
        Produto produto = update(request, id);

        return ProdutoResponse.from(repository.update(produto));
    }

    private Produto update(ProdutoRequest request, String id) {

        CategoriaEnum categoria = verificarCategoriaExistente(request.getCategoria());
        Optional<Produto> produto = findByProdutoIdUseCase.findById(convertToUUID(id));

        if(produto.isEmpty()){
            throw new NotFoundProdutoException("Produto não encontrado");
        }
        Produto updateProduto = produto.get();

        updateProduto.setCategoria(categoria);
        updateProduto.setNome(request.getNome());
        updateProduto.setPrecoBase(new BigDecimal(request.getPreco_base()).setScale(2, RoundingMode.HALF_DOWN));
        updateProduto.setPrecoTarifado(calcular(updateProduto.getPrecoBase(), categoria));

        return updateProduto;
    }

    public UUID convertToUUID(String uuid){
        try {
            return UUID.fromString(uuid);
        } catch (IllegalArgumentException e) {
            throw new NotFoundProdutoException("Formato do ID incorreto");
        }
    }

    private CategoriaEnum verificarCategoriaExistente(String categoria) {
        Optional<CategoriaEnum> categorias = CategoriaEnum.convertToEnum(categoria);

        if (categorias.isEmpty())
            throw new NotFoundCategoriaException("Categoria não encontrada");

        return categorias.get();
    }

    private BigDecimal calcular(BigDecimal precoBase, CategoriaEnum categoria) {
        return calcularTarifa.calcular(precoBase, categoria);
    }



}
