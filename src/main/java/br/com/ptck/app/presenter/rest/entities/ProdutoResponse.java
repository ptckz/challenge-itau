package br.com.ptck.app.presenter.rest.entities;

import java.math.BigDecimal;
import java.util.UUID;

import br.com.ptck.app.core.Produto;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProdutoResponse {

    private final UUID id;
    private final String nome;
    private final String categoria;
    private final BigDecimal preco_base;
    private final BigDecimal preco_tarifado;

    public static ProdutoResponse from(Produto produto) {
        return new ProdutoResponse(
            produto.getId(),
            produto.getNome(),
            produto.getCategoria().toString(),
            produto.getPrecoBase(),
            produto.getPrecoTarifado());
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public BigDecimal getPreco_base() {
        return preco_base;
    }

    public BigDecimal getPreco_tarifado() {
        return preco_tarifado;
    }

}
