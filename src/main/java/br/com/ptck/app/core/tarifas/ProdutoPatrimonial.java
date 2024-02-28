package br.com.ptck.app.core.tarifas;

import java.math.BigDecimal;

public class ProdutoPatrimonial extends MediatorCalculoTarifaImpl {

    private static BigDecimal iof = new BigDecimal(5 / 100.0);
    private static BigDecimal pis = new BigDecimal(3 / 100.0);
    private static BigDecimal confins = new BigDecimal(0 / 100.0);

    public BigDecimal calcularTarifa(BigDecimal precoBase){
        return new MediatorCalculoTarifaImpl().calcularTarifa(precoBase, iof, pis, confins);
    }

}
