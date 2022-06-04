package br.com.razes.bytecode.model.transactions.transaction;



import br.com.razes.bytecode.model.transactions.Transactions;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;

@Entity
public class CoinTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "coin_transaction_sequence")
    @SequenceGenerator(name = "coin_transaction_sequence", sequenceName = "coin_transaction_sequence", allocationSize = 1)
    private Long id;

    private BigInteger amount = BigInteger.ZERO;

    private String  symbolFromCoin;

    private String symbolToCoin;

    private Long rate;

    @ManyToOne
    private Transactions transactions;

    private final ZonedDateTime register = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));

    public CoinTransaction(){

    }
    public CoinTransaction(BigInteger amount, String symbolFromCoin, String symbolToCoin, Transactions transactions) {
        this.amount = amount;
        this.symbolFromCoin = symbolFromCoin;
        this.symbolToCoin = symbolToCoin;
        this.rate = getCurrentRate();
        this.transactions = transactions;
    }
    private Long getCurrentRate() {
        long currentRate = 0L;
        currentRate =+ 1L;
        return currentRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoinTransaction that = (CoinTransaction) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    private void setAmount(BigInteger amount) {
        this.amount = amount;
    }

    private void setSymbolFromCoin(String symbolFromCoin) {
        this.symbolFromCoin = symbolFromCoin;
    }

    private void setSymbolToCoin(String symbolToCoin) {
        this.symbolToCoin = symbolToCoin;
    }


    public Long getId() {
        return id;
    }

    public BigInteger getAmount() {
        return amount;
    }

    public String getSymbolFromCoin() {
        return symbolFromCoin;
    }

    public String getSymbolToCoin() {
        return symbolToCoin;
    }

    public Long getRate() {
        return rate;
    }

    public Transactions getTransactions() {
        return transactions;
    }

    public void setTransactions(Transactions transactions) {
        this.transactions = transactions;
    }

    public ZonedDateTime getRegister() {
        return register;
    }
}
