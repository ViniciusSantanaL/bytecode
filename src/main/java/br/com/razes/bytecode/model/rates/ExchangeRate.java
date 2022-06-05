package br.com.razes.bytecode.model.rates;

import javax.persistence.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class ExchangeRate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "exchange_rate_sequence")
    @SequenceGenerator(name = "exchange_rate_sequence", sequenceName = "exchange_rate_sequence", allocationSize = 1)
    private Long id;

    private String baseCoin;

    @OneToMany(mappedBy = "exchangeRate",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private final Set<CurrentRate> currentRates = new HashSet<CurrentRate>();

    private boolean active = false;

    private ZonedDateTime updateRate;

    public ExchangeRate(){
    }

    public ExchangeRate(String baseCoin){
        this.baseCoin = baseCoin;
    }

    public Long getId() {
        return id;
    }

    public String getBaseCoin() {
        return baseCoin;
    }

    public Set<CurrentRate> getCurrentRates() {
        return currentRates;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public ZonedDateTime getUpdateRate() {
        return updateRate;
    }

    public void setUpdateRate(ZonedDateTime updateRate) {
        this.updateRate = updateRate;
    }
}
