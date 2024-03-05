package br.com.ptck.app.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.ptck.app.TestGenerator;
import br.com.ptck.app.core.gateway.ProdutoRepository;
import br.com.ptck.app.core.usecase.GetProdutoByIdUseCase;

@ExtendWith(MockitoExtension.class)
public class GetProdutoByIdUseCaseTest {

    @Spy
    @InjectMocks
    GetProdutoByIdUseCase getProdutoByIdUseCase;

    @Mock
    ProdutoRepository repository;

    Produto expected;

    @BeforeEach
    void setUp() {
        expected = TestGenerator.randomProduto();
    }

    @Test
    void whenExecuteGetProdutoByIdUseCaseReturnProdutoTest() {

        GetProdutoByIdUseCase.InputValues input = new GetProdutoByIdUseCase.InputValues(expected.getId());
        GetProdutoByIdUseCase.OutputValues output = new GetProdutoByIdUseCase.OutputValues(Optional.of(expected));

        doReturn(output)
                .when(getProdutoByIdUseCase)
                .execute(eq(input));

        Optional<Produto> actual = getProdutoByIdUseCase.execute(input).getOptionalProduto();

        assertEquals(expected.getId(), actual.get().getId());
        assertEquals(expected.getNome(), actual.get().getNome());
        assertEquals(expected.getCategoria(), actual.get().getCategoria());
        assertEquals(expected.getPrecoBase(), actual.get().getPrecoBase());
        assertEquals(expected.getPrecoTarifado(), actual.get().getPrecoTarifado());

    }

}