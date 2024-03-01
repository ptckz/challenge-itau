package br.com.ptck.app.core.tarifas;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MediatorCalculoTarifaImpl implements IMediatorCalculoTarifa {

    @Override
    public BigDecimal calcular(BigDecimal precoBase, BigDecimal iof, BigDecimal pis, BigDecimal confins) {
        return precoBase
                .add(precoBase.multiply(iof))
                .add(precoBase.multiply(pis))
                .add(precoBase.multiply(confins))
                .setScale(2, RoundingMode.HALF_DOWN);
    }

}
