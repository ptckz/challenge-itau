package br.com.ptck.app.presenter.rest.entities;

import br.com.ptck.app.core.Produto;

public class ProdutoRequest {
    private final String nome;
    private final String categoria;
    private final Double preco_base;
    private final Double preco_tarifado;

    public static ProdutoRequest from(Produto produto) {
        return new ProdutoRequest(
                produto.getNome(),
                produto.getCategoria().toString(),
                produto.getPrecoBase().doubleValue());
    }

    public ProdutoRequest(){
        this.nome = null;
        this.categoria = null;
        this.preco_base = null;
        this.preco_tarifado = null;
    }

    public ProdutoRequest(String nome, String categoria, Double preco_base) {
        this.nome = nome;
        this.categoria = categoria;
        this.preco_base = preco_base;
        this.preco_tarifado = 0.0;
    }

    public String getNome() {
        return nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public Double getPreco_base() {
        return preco_base;
    }

    public Double getPreco_tarifado() {
        return preco_tarifado;
    }
    

}
