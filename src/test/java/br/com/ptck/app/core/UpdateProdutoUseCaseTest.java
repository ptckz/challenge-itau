package br.com.ptck.app.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.ptck.app.TestGenerator;
import br.com.ptck.app.core.exception.NotFoundProdutoException;
import br.com.ptck.app.core.gateway.ProdutoRepository;
import br.com.ptck.app.core.usecase.GetProdutoByIdUseCase;
import br.com.ptck.app.core.usecase.UpdateProdutoUseCase;
import br.com.ptck.app.presenter.rest.entities.ProdutoRequest;
import br.com.ptck.app.presenter.rest.entities.ProdutoResponse;

@ExtendWith(MockitoExtension.class)
public class UpdateProdutoUseCaseTest {

    @InjectMocks
    UpdateProdutoUseCase useCase;
    
    @Mock
    GetProdutoByIdUseCase getProdutoByIdUseCase;

    @Mock
    ProdutoRepository repository;

    Produto expectedProduto;
    ProdutoRequest request;

    @BeforeEach
    void setUp() {
        expectedProduto = TestGenerator.randomProduto();
    }

    @Test
    void whenExecuteGetProdutoByIdUseCaseShouldThrowExceptionWhenProdutoIsNotFoundTest(){
        GetProdutoByIdUseCase.InputValues getProtudoInput =
        new GetProdutoByIdUseCase.InputValues(expectedProduto.getId());

        GetProdutoByIdUseCase.OutputValues getProdutosOutput =
        new GetProdutoByIdUseCase.OutputValues(Optional.empty());

        UpdateProdutoUseCase.InputValues useCaseInput = new UpdateProdutoUseCase.InputValues(
                expectedProduto.getId().toString(),
                expectedProduto.getNome(),
                expectedProduto.getCategoria().toString(),
                expectedProduto.getPrecoBase().doubleValue());

        doReturn(getProdutosOutput)
            .when(getProdutoByIdUseCase)
            .execute(eq(getProtudoInput));

        NotFoundProdutoException notFoundProdutoException = assertThrows(NotFoundProdutoException.class, () -> {
            useCase.execute(useCaseInput);
        });

        assertTrue(notFoundProdutoException.getMessage().contains("Produto não encontrado"));

    }

    @Test
    void updateProdutoUseCaseTest() {
 
        GetProdutoByIdUseCase.InputValues getProtudoInput =
            new GetProdutoByIdUseCase.InputValues(expectedProduto.getId());

        GetProdutoByIdUseCase.OutputValues getProdutosOutput =
            new GetProdutoByIdUseCase.OutputValues(Optional.of(expectedProduto));

        UpdateProdutoUseCase.InputValues useCaseInput = new UpdateProdutoUseCase.InputValues(
                expectedProduto.getId().toString(),
                expectedProduto.getNome(),
                expectedProduto.getCategoria().toString(),
                expectedProduto.getPrecoBase().doubleValue());
        
        doReturn(getProdutosOutput)
            .when(getProdutoByIdUseCase)
            .execute(eq(getProtudoInput));
        
        doReturn(expectedProduto)
                .when(repository)
                .persist(expectedProduto);

        ProdutoResponse response = useCase.execute(useCaseInput).getProduto();

        // then
        assertEquals(response.getId(), expectedProduto.getId());
        assertEquals(response.getNome(), expectedProduto.getNome());
        assertEquals(response.getCategoria(), expectedProduto.getCategoria().toString());
        assertEquals(response.getPreco_base(), expectedProduto.getPrecoBase());
        assertEquals(response.getPreco_tarifado(), expectedProduto.getPrecoTarifado());

    }

}
