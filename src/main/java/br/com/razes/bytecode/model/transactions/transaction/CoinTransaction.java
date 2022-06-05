package br.com.razes.bytecode.model.transactions.transaction;



import br.com.razes.bytecode.model.transactions.Transactions;

import javax.persistence.*;
import java.math.BigDecimal;
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

    private BigDecimal amount;

    private String  symbolFromCoin;

    private String symbolToCoin;

    private BigDecimal result;

    private BigDecimal rate;

    @ManyToOne
    private Transactions transactions;

    private final ZonedDateTime register = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));

    public CoinTransaction(){

    }
    public CoinTransaction(BigDecimal amount, String symbolFromCoin, String symbolToCoin,
        Transactions transactions, BigDecimal result ,BigDecimal rate) {
        this.amount = amount;
        this.symbolFromCoin = symbolFromCoin;
        this.symbolToCoin = symbolToCoin;
        this.result = result;
        this.rate = rate;
        this.transactions = transactions;
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


    public Long getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getSymbolFromCoin() {
        return symbolFromCoin;
    }

    public String getSymbolToCoin() {
        return symbolToCoin;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public Transactions getTransactions() {
        return transactions;
    }
    public ZonedDateTime getRegister() {
        return register;
    }

    public BigDecimal getResult() {
        return result;
    }
}
