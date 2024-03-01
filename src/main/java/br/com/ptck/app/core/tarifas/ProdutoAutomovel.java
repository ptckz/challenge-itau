package br.com.ptck.app.core.tarifas;

import java.math.BigDecimal;

public class ProdutoAutomovel extends MediatorCalculoTarifaImpl implements IProduto {
    
    private static BigDecimal iof = new BigDecimal(5.5 / 100.0);
    private static BigDecimal pis = new BigDecimal(4 / 100.0);
    private static BigDecimal confins = new BigDecimal(1 / 100.0);

    @Override
    public BigDecimal calcularTarifa(BigDecimal precoBase){
        return new ProdutoAutomovel().calcular(precoBase, iof, pis, confins);
    }

}
