package br.com.ptck.app.presenter.rest.entities;

import lombok.Value;

@Value
public class ProdutoRequest {
    private final String id;
    private final String nome;
    private final String categoria;
    private final Double preco_base;
    private final Double preco_tarifado;
}
