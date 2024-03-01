package br.com.ptck.app.data.entities;

import java.math.BigDecimal;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.domain.Persistable;

import br.com.ptck.app.core.Produto;
import br.com.ptck.app.core.Produto.CategoriaEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

@Value
@Entity
@Table(name = "ProdutoData")
@ToString(of = {"nome", "categoria", "precoBase", "precoTarifado"})
@EqualsAndHashCode(of = {"nome", "categoria", "precoBase", "precoTarifado"})
public class ProdutoData implements Persistable<UUID> {
    
    @Id 
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    
    @Column(name = "nome", nullable = false)
    private String nome;
    
    @Column(name = "categoria", nullable = false)
    private CategoriaEnum categoria;
    
    @Column(name = "precoBase", nullable = false)
    private BigDecimal precoBase;

    @Column(name = "precoTarifado", nullable = false)
    private BigDecimal precoTarifado;

    public ProdutoData(UUID id, String nome, CategoriaEnum categoria, BigDecimal precoBase, BigDecimal precoTarifado) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.precoBase = precoBase;
        this.precoTarifado = precoTarifado;
    }

    public ProdutoData(){
        this.precoTarifado = null;
        this.precoBase = null;
        this.nome = "";
        this.id = null;
        this.categoria = null;
    }

    public Produto fromThis(){
        return new Produto(
            this.id,
            this.nome,
            this.categoria,
            this.precoBase,
            this.precoTarifado
        );
    }

    public Produto toProduto(){
        Produto produto = new Produto(
            id,
            nome,
            categoria, 
            precoBase,
            precoTarifado 
        );
        produto.setId(this.getId()); 
        return produto;
    }

    public static ProdutoData from(Produto produto){
        ProdutoData produtoData = new ProdutoData(
            produto.getId(),
            produto.getNome(),
            produto.getCategoria(),
            produto.getPrecoBase(),
            produto.getPrecoTarifado()
        );
        return produtoData;
    }

    @Override
    public boolean isNew() {
        return id == null;
    }

}
