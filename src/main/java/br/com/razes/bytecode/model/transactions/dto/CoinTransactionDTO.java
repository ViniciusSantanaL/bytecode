package br.com.razes.bytecode.model.transactions.dto;

import br.com.razes.bytecode.model.transactions.transaction.CoinTransaction;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class CoinTransactionDTO {

    private final Long id;
    private final BigDecimal amount;

    private final String from;

    private final String to;

    private final BigDecimal rate;

    private final ZonedDateTime register;

    public CoinTransactionDTO(CoinTransaction coinTransaction) {
        this.id = coinTransaction.getId();
        this.amount = coinTransaction.getAmount();
        this.from = coinTransaction.getSymbolFromCoin();
        this.to = coinTransaction.getSymbolToCoin();
        this.rate = coinTransaction.getRate();
        this.register = coinTransaction.getRegister();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public ZonedDateTime getRegister() {
        return register;
    }

    public Long getId() {
        return id;
    }
}
