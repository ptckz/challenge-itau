package br.com.ptck.app.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Configuration;

import br.com.ptck.app.TestGenerator;
import br.com.ptck.app.TestUtils;
import br.com.ptck.app.core.Produto;
import br.com.ptck.app.data.entities.ProdutoData;
import br.com.ptck.app.data.repositories.JpaProdutoRepository;
import br.com.ptck.app.data.repositories.ProdutoRepositoryImpl;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
public class ProdutoRepositoryImplTest {

    @Configuration
    @EntityScan("br.com.ptck.app.data.entities")
    @AutoConfigurationPackage
    static class Config {
    }

    @InjectMocks
    ProdutoRepositoryImpl produtoRepository;

    @Mock
    JpaProdutoRepository jpaProdutoRepository;

    @Test
    void getByIdReturnProdutoData(){

        Produto expected = TestGenerator.randomProduto();
        ProdutoData toBeReturned = ProdutoData.from(expected);

        doReturn(Optional.of(toBeReturned))
            .when(jpaProdutoRepository)
            .findById(eq(expected.getId()));

        Optional<Produto> actual = produtoRepository.findById(expected.getId());

        assertEquals(actual.isPresent(), true);

    }

    @Disabled
    @Test
    void persistReturnProdutoData() throws Exception {

        Produto expected = TestGenerator.randomProduto();
        
        ProdutoData toBeReturned = ProdutoData.from(expected);
        ProdutoData toBeMatched = TestUtils.newInstanceWithProperties(ProdutoData.class, toBeReturned);

        doReturn(toBeReturned)
                .when(jpaProdutoRepository)
                .save(toBeMatched);

        Produto actual = produtoRepository.persist(expected);

        assertEquals(expected, actual);

    }
}
