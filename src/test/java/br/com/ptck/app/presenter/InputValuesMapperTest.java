package br.com.ptck.app.presenter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.ptck.app.TestGenerator;
import br.com.ptck.app.core.usecase.CreateProdutoUseCase;
import br.com.ptck.app.presenter.rest.entities.ProdutoRequest;

public class InputValuesMapperTest {
    
    @Test
    void mapReturnCreateProdutoMapper(){
        ProdutoRequest request = ProdutoRequest.from(TestGenerator.randomProduto());

        CreateProdutoUseCase.InputValues actual = 
            new CreateProdutoUseCase.InputValues(request.getNome(), request.getCategoria(), request.getPreco_base());

       assertEquals(actual.getNome(), request.getNome());
       assertEquals(actual.getCategoria(), request.getCategoria());
       assertEquals(actual.getPrecoBase(), request.getPreco_base());    
    }
}
