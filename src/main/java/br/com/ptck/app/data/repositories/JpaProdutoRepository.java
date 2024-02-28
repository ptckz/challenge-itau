package br.com.ptck.app.data.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ptck.app.data.entities.ProdutoData;
import br.com.ptck.app.core.Produto.CategoriaEnum;


public interface JpaProdutoRepository extends JpaRepository<ProdutoData, UUID> {
    Optional<ProdutoData> findByCategoria(CategoriaEnum categoria);
}
