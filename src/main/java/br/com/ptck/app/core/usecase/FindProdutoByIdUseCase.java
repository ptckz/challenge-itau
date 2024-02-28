package br.com.ptck.app.core.usecase;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import br.com.ptck.app.core.Produto;
import br.com.ptck.app.core.gateway.ProdutoRepository;

@Component
public class FindProdutoByIdUseCase {

    private ProdutoRepository repository;

    public FindProdutoByIdUseCase(ProdutoRepository repository){
        this.repository = repository;
    }

    public Optional<Produto> findById(UUID id) {
        return repository.findById(id);
    }

}
