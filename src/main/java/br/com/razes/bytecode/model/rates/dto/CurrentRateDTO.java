package br.com.razes.bytecode.model.rates.dto;

import br.com.razes.bytecode.model.rates.CurrentRate;

import java.math.BigDecimal;

public class CurrentRateDTO {

    private final String symbol;

    private final BigDecimal rate;

    public CurrentRateDTO(CurrentRate currentRate) {
        this.symbol = currentRate.getSymbol();
        this.rate = currentRate.getRate();
    }

    public String getSymbol() {
        return symbol;
    }

    public BigDecimal getRate() {
        return rate;
    }
}
