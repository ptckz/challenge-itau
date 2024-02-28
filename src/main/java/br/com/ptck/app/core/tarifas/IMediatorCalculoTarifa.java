package br.com.ptck.app.core.tarifas;

import java.math.BigDecimal;

public interface IMediatorCalculoTarifa {

    public BigDecimal calcularTarifa(BigDecimal precoBase, BigDecimal iof, BigDecimal pis, BigDecimal confins);

}
