package br.com.ptck.app.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import br.com.ptck.app.TestGenerator;
import br.com.ptck.app.core.Produto.CategoriaEnum;

public class ProdutoTest {
    

    @Test
    public void newInstanceTest() throws InterruptedException {
        UUID id = TestGenerator.randomId();
        String nome = TestGenerator.randomNome();
        CategoriaEnum categoria = TestGenerator.randomCategoria();
        BigDecimal precoBase = TestGenerator.randomPreco();
        BigDecimal precoTarifado = TestGenerator.randomPreco();

        Produto actual = Produto.newInstance(id, nome, categoria, precoBase, precoTarifado);

        assertEquals(actual.getId(), id);
        assertEquals(actual.getNome(), nome);
        assertEquals(actual.getCategoria(), categoria);
        assertEquals(actual.getPrecoBase(), precoBase);
        assertEquals(actual.getPrecoTarifado(), precoTarifado);
        
    }
}
