package br.com.razes.bytecode.model.rates;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
public class CurrentRate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "current_rate_sequence")
    @SequenceGenerator(name = "current_rate_sequence", sequenceName = "current_rate_sequence", allocationSize = 1)
    private Long id;

    private String symbol;

    private String rate = "0.000000";

    @ManyToOne
    private ExchangeRate exchangeRate;

    public CurrentRate(){}

    public CurrentRate(String symbol, ExchangeRate exchangeRate) {
        this.symbol = symbol;
        this.exchangeRate = exchangeRate;
    }

    public Long getId() {
        return id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;

    }
    public ExchangeRate getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(ExchangeRate exchangeRate) {
        this.exchangeRate = exchangeRate;
    }
}
