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


@Entity
@Table(name = "ProdutoData")
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
            nome,
            categoria, 
            precoBase,
            precoTarifado 
        );
        produto.setId(this.getId()); 
        return produto;
    }

    public static ProdutoData from(Produto produto){
        return new ProdutoData(
            produto.getId(),
            produto.getNome(),
            produto.getCategoria(),
            produto.getPrecoBase(),
            produto.getPrecoTarifado()
        );
    }

    public ProdutoData(){

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public CategoriaEnum getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEnum categoria) {
        this.categoria = categoria;
    }

    public BigDecimal getPrecoBase() {
        return precoBase;
    }

    public void setPrecoBase(BigDecimal precoBase) {
        this.precoBase = precoBase;
    }

    public BigDecimal getPrecoTarifado() {
        return precoTarifado;
    }

    public void setPrecoTarifado(BigDecimal precoTarifado) {
        this.precoTarifado = precoTarifado;
    }

    @Override
    public boolean isNew() {
        return id == null;
    }

}
