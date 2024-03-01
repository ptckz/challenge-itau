package br.com.ptck.app.core.tarifas;

import java.math.BigDecimal;

public class ProdutoViagem extends MediatorCalculoTarifaImpl implements IProduto {

    private static BigDecimal iof = new BigDecimal(2 / 100.0);
    private static BigDecimal pis = new BigDecimal(4 / 100.0);
    private static BigDecimal confins = new BigDecimal(1 / 100.0);

    @Override
    public BigDecimal calcularTarifa(BigDecimal precoBase){
        return new MediatorCalculoTarifaImpl().calcular(precoBase, iof, pis, confins);
    }
    
}
