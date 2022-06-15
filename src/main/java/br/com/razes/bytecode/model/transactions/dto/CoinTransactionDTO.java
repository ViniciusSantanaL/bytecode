package br.com.razes.bytecode.model.transactions.dto;

import br.com.razes.bytecode.model.transactions.transaction.CoinTransaction;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class CoinTransactionDTO {

    private final Long id;
    private final BigDecimal amount;

    private final String fromSymbol;

    private final String toSymbol;

    private final String rate;

    private final ZonedDateTime register;

    private final String result;

    public CoinTransactionDTO(CoinTransaction coinTransaction) {
        this.id = coinTransaction.getId();
        this.amount = coinTransaction.getAmount();
        this.fromSymbol = coinTransaction.getSymbolFromCoin();
        this.toSymbol = coinTransaction.getSymbolToCoin();
        this.rate = coinTransaction.getRate();
        this.register = coinTransaction.getRegister();
        this.result = coinTransaction.getResult();
    }

    public BigDecimal getAmount() {
        return amount;
    }


    public String getFromSymbol() {
        return fromSymbol;
    }

    public String getToSymbol() {
        return toSymbol;
    }

    public String getRate() {
        return rate;
    }

    public ZonedDateTime getRegister() {
        return register;
    }

    public Long getId() {
        return id;
    }

    public String getResult() {
        return result;
    }
}
