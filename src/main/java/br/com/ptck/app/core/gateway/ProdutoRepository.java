package br.com.ptck.app.core.gateway;

import java.util.Optional;
import java.util.UUID;

import br.com.ptck.app.core.Produto;

public interface ProdutoRepository {

    Produto persist(Produto produto);
    Produto update(Produto produto);
    Optional<Produto> findById(UUID id);
    
}