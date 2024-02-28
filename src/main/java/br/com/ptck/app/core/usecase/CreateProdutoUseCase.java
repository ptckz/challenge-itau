package br.com.ptck.app.core.usecase;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

import br.com.ptck.app.core.Produto;
import br.com.ptck.app.core.Produto.CategoriaEnum;
import br.com.ptck.app.core.exception.NotFoundCategoriaException;
import br.com.ptck.app.core.exception.NotFoundProdutoException;
import br.com.ptck.app.core.gateway.ProdutoRepository;
import br.com.ptck.app.presenter.rest.entities.ProdutoRequest;
import br.com.ptck.app.presenter.rest.entities.ProdutoResponse;

public class CreateProdutoUseCase implements UseCase<ProdutoRequest, ProdutoResponse>{

    private ProdutoRepository repository;
    private CalcularTarifaProdutoUseCase calcularTarifa;

    public CreateProdutoUseCase(ProdutoRepository repository, 
                                CalcularTarifaProdutoUseCase calcularTarifa) {
        this.calcularTarifa = calcularTarifa;
        this.repository = repository;
    }

    @Override
    public ProdutoResponse execute(ProdutoRequest request, String id) {
        Produto produto = create(request);

        return ProdutoResponse.from(repository.persist(produto));
    }

    private Produto create(ProdutoRequest request) {
        CategoriaEnum categoria = verificarCategoriaExistente(request.getCategoria());

        Produto createProduto = Produto.newInstance(
                request.getNome(),
                categoria,
                new BigDecimal(request.getPreco_base()).setScale(2, RoundingMode.HALF_DOWN));
        createProduto.setPrecoTarifado(calcular(createProduto.getPrecoBase(), categoria));
        return createProduto;
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
