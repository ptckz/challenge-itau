package br.com.ptck.app.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Configuration;

import br.com.ptck.app.TestGenerator;
import br.com.ptck.app.data.entities.ProdutoData;
import br.com.ptck.app.data.repositories.JpaProdutoRepository;

@DataJpaTest
public class JpaProdutoRepositoryTest {

    @Configuration
    @EntityScan("br.com.ptck.app.data.entities")
    @AutoConfigurationPackage
    static class Config {
    }

    @Autowired
    JpaProdutoRepository repository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    void saveProduto() {

        ProdutoData produtoData = new ProdutoData(null, TestGenerator.randomNome(),
                TestGenerator.randomCategoria(),
                TestGenerator.randomPreco(),
                TestGenerator.randomPreco());
        ProdutoData toSavedProdutoData = entityManager.persistAndFlush(produtoData);

        ProdutoData savedProdutoData = repository.save(toSavedProdutoData);

        assertEquals(savedProdutoData.getId(), produtoData.getId());

    }

}
