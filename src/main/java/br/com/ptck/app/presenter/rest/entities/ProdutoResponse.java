package br.com.ptck.app.presenter.rest.entities;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import br.com.ptck.app.core.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
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

    public static List<ProdutoResponse> from(List<Produto> produtos) {
        return produtos.parallelStream()
                    .map(ProdutoResponse::from)
                    .collect(Collectors.toList());
    }

}
