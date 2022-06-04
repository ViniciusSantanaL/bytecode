package br.com.razes.bytecode.model.rates;

import javax.persistence.*;

@Entity
public class CurrentRate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "current_rate_sequence")
    @SequenceGenerator(name = "current_rate_sequence", sequenceName = "current_rate_sequence", allocationSize = 1)
    private Long id;

    private String symbol;

    private Long rate;

    @ManyToOne
    private ExchangeRate exchangeRate;

    public CurrentRate(){}

    public CurrentRate(String symbol, Long rate, ExchangeRate exchangeRate) {
        this.symbol = symbol;
        this.rate = rate;
        this.exchangeRate = exchangeRate;
    }
}
