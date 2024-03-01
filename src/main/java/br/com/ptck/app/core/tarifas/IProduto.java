package br.com.ptck.app.core.tarifas;

import java.math.BigDecimal;

public interface IProduto {

    public BigDecimal calcularTarifa(BigDecimal precoBase);

}
