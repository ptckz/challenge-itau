package br.com.ptck.app.presenter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.ptck.app.core.gateway.ProdutoRepository;
import br.com.ptck.app.core.usecase.CreateProdutoUseCase;
import br.com.ptck.app.core.usecase.GetProdutoByIdUseCase;
import br.com.ptck.app.core.usecase.UpdateProdutoUseCase;

@Configuration
public class Modules {
    
    @Bean
    public CreateProdutoUseCase createProdutoUseCase(ProdutoRepository produtoRepository){
        return new CreateProdutoUseCase(produtoRepository);
    }

    @Bean
    public UpdateProdutoUseCase updateProdutoUseCase(ProdutoRepository produtoRepository,  
                                                     GetProdutoByIdUseCase getProdutoByIdUseCase){
        return new UpdateProdutoUseCase(produtoRepository, getProdutoByIdUseCase);
    }

    @Bean
    public GetProdutoByIdUseCase getProdutoByIdUseCase(ProdutoRepository produtoRepository){
        return new GetProdutoByIdUseCase(produtoRepository);
    }
}
