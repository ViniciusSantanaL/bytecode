package br.com.razes.bytecode.model.rates;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ExchangeRate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "exchange_rate_sequence")
    @SequenceGenerator(name = "exchange_rate_sequence", sequenceName = "exchange_rate_sequence", allocationSize = 1)
    private Long id;

    private String symbol;

    @OneToMany(mappedBy = "exchangeRate")
    private List<CurrentRate> currentRates = new ArrayList<CurrentRate>();

    private boolean active = false;

    public ExchangeRate(){}

    public Long getId() {
        return id;
    }

    public String getSymbol() {
        return symbol;
    }

    public List<CurrentRate> getCurrentRates() {
        return currentRates;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
