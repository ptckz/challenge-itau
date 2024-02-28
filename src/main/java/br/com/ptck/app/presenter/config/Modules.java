package br.com.ptck.app.presenter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.ptck.app.core.gateway.ProdutoRepository;
import br.com.ptck.app.core.usecase.CalcularTarifaProdutoUseCase;
import br.com.ptck.app.core.usecase.CreateProdutoUseCase;
import br.com.ptck.app.core.usecase.FindProdutoByIdUseCase;
import br.com.ptck.app.core.usecase.UpdateProdutoUseCase;

@Configuration
public class Modules {
    
    @Bean
    public CreateProdutoUseCase createProdutoUseCase(ProdutoRepository produtoRepository){
        CalcularTarifaProdutoUseCase calcularTarifaProdutoUseCase = new CalcularTarifaProdutoUseCase();
        return new CreateProdutoUseCase(produtoRepository, calcularTarifaProdutoUseCase);
    }

    @Bean
    public UpdateProdutoUseCase updateProdutoUseCase(ProdutoRepository produtoRepository,  
                                                    FindProdutoByIdUseCase findByCategoriaUseCase){
        CalcularTarifaProdutoUseCase calcularTarifaProdutoUseCase = new CalcularTarifaProdutoUseCase();
        return new UpdateProdutoUseCase(produtoRepository, calcularTarifaProdutoUseCase, findByCategoriaUseCase);
    }

    @Bean
    public FindProdutoByIdUseCase findProdutoByCategoriaUseCase(ProdutoRepository produtoRepository){
        return new FindProdutoByIdUseCase(produtoRepository);
    }
}
