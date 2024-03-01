package br.com.ptck.app.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.ptck.app.TestGenerator;
import br.com.ptck.app.core.exception.NotFoundCategoriaException;
import br.com.ptck.app.core.gateway.ProdutoRepository;
import br.com.ptck.app.core.usecase.CreateProdutoUseCase;
import br.com.ptck.app.presenter.rest.entities.ProdutoResponse;

@ExtendWith(MockitoExtension.class)
public class CreateProdutoUseCaseTest {

    @InjectMocks
    private CreateProdutoUseCase useCase;

    @Mock
    private ProdutoRepository repository;

    Produto expectedProduto;

    @BeforeEach
    void setUp() {
        expectedProduto = TestGenerator.randomProduto();
    }

    @Test
    void createProdutoUseCaseTest() {

        CreateProdutoUseCase.InputValues useCaseInput = new CreateProdutoUseCase.InputValues(expectedProduto.getNome(),
                expectedProduto.getCategoria().toString(), expectedProduto.getPrecoBase().doubleValue());

        doReturn(expectedProduto)
                .when(repository)
                .persist(any(Produto.class));

        ProdutoResponse response = useCase.execute(useCaseInput).getProduto();

        // then
        assertEquals(response.getNome(), expectedProduto.getNome());
        assertEquals(response.getCategoria(), expectedProduto.getCategoria().toString());
        assertEquals(response.getPreco_base(), expectedProduto.getPrecoBase());

    }

    @Test
    void executeThrowExceptionWhenCategoriaIsNotFound(){
        CreateProdutoUseCase.InputValues useCaseInput = new CreateProdutoUseCase.InputValues(
            expectedProduto.getNome(),
            "QWERTY",
            expectedProduto.getPrecoBase().doubleValue());

        NotFoundCategoriaException notFoundProdutoException = assertThrows(NotFoundCategoriaException.class, () -> {
            useCase.execute(useCaseInput).getProduto();
        });

        assertTrue(notFoundProdutoException.getMessage().contains("Categoria não encontrada"));

    }

}
