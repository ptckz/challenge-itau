package br.com.ptck.app.core.usecase;

import java.math.BigDecimal;

import br.com.ptck.app.core.Produto.CategoriaEnum;
import br.com.ptck.app.core.tarifas.ProdutoAutomovel;
import br.com.ptck.app.core.tarifas.ProdutoPatrimonial;
import br.com.ptck.app.core.tarifas.ProdutoResidencial;
import br.com.ptck.app.core.tarifas.ProdutoViagem;
import br.com.ptck.app.core.tarifas.ProdutoVida;

public class CalcularTarifaProdutoUseCase {

    public BigDecimal calcular(BigDecimal valorBase, CategoriaEnum categoriaEnum) {
        return calcularTarifa(valorBase, categoriaEnum);
    }

    private BigDecimal calcularTarifa(BigDecimal precoBase, CategoriaEnum categoria) {
        switch (categoria) {
            case VIDA:
                return new ProdutoVida().calcularTarifa(precoBase);
            case AUTO:
                return new ProdutoAutomovel().calcularTarifa(precoBase);
            case PATRIMONIAL:
                return new ProdutoPatrimonial().calcularTarifa(precoBase);
            case RESIDENCIAL:
                return new ProdutoResidencial().calcularTarifa(precoBase);
            case VIAGEM:
                return new ProdutoViagem().calcularTarifa(precoBase);
        }
        return null;
    }

}
