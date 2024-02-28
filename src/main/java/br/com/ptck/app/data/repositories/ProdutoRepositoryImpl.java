package br.com.ptck.app.data.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import br.com.ptck.app.core.Produto;
import br.com.ptck.app.core.Produto.CategoriaEnum;
import br.com.ptck.app.core.gateway.ProdutoRepository;
import br.com.ptck.app.data.entities.ProdutoData;

@Repository
public class ProdutoRepositoryImpl implements ProdutoRepository {
    private JpaProdutoRepository repository;

    public ProdutoRepositoryImpl(JpaProdutoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Produto persist(Produto produto) {
        ProdutoData produtoData = ProdutoData.from(produto);

        return repository.save(produtoData).fromThis();
    }

    @Override
    public Produto update(Produto produto) {
        ProdutoData produtoData = ProdutoData.from(produto);

        return repository.save(produtoData).fromThis();
    }

    @Override
    public Optional<Produto> findById(UUID id) {
        Optional<ProdutoData> produtoData = repository.findById(id);
        
        Optional<Produto> produto = produtoData.map(p -> {
            return p.fromThis();
        });

        return produto;
    }

}
