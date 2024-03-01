package br.com.ptck.app.core.tarifas;

import java.math.BigDecimal;

public class ProdutoPatrimonial extends MediatorCalculoTarifaImpl implements IProduto { 

    private static BigDecimal iof = new BigDecimal(5 / 100.0);
    private static BigDecimal pis = new BigDecimal(3 / 100.0);
    private static BigDecimal confins = new BigDecimal(0 / 100.0);

    @Override
    public BigDecimal calcularTarifa(BigDecimal precoBase){
        return new MediatorCalculoTarifaImpl().calcular(precoBase, iof, pis, confins);
    }

}
