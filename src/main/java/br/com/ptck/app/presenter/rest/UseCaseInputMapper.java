package br.com.ptck.app.presenter.rest;

import br.com.ptck.app.core.usecase.CreateProdutoUseCase;
import br.com.ptck.app.core.usecase.UpdateProdutoUseCase;
import br.com.ptck.app.presenter.rest.entities.ProdutoRequest;

public final class UseCaseInputMapper {

    public static CreateProdutoUseCase.InputValues map(ProdutoRequest request) {
        return new CreateProdutoUseCase.InputValues(
            request.getNome(),
            request.getCategoria(),
            request.getPreco_base());
    }

    public static UpdateProdutoUseCase.InputValues map(ProdutoRequest request, String id) {
        return new UpdateProdutoUseCase.InputValues(
            id,
            request.getNome(),
            request.getCategoria(),
            request.getPreco_base());
    }

}
