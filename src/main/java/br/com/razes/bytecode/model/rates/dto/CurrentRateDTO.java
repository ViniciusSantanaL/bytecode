package br.com.razes.bytecode.model.rates.dto;

import br.com.razes.bytecode.model.rates.CurrentRate;

import java.math.BigDecimal;

public class CurrentRateDTO {

    private final String symbol;

    private final String rate;

    public CurrentRateDTO(CurrentRate currentRate) {
        this.symbol = currentRate.getSymbol();
        this.rate = currentRate.getRate();
    }

    public String getSymbol() {
        return symbol;
    }

    public String getRate() {
        return rate;
    }
}
