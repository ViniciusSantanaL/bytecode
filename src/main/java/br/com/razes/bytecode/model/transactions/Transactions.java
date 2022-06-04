package br.com.razes.bytecode.model.transactions;

import br.com.razes.bytecode.model.coin.CoinType;
import br.com.razes.bytecode.model.transactions.transaction.CoinTransaction;

import javax.persistence.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "transactions_sequence")
    @SequenceGenerator(name = "transactions_sequence", sequenceName = "transactions_sequence", allocationSize = 1)
    private Long id;

    @OneToMany(mappedBy = "transactions")
    private final List<CoinTransaction> coinTransactions = new ArrayList<CoinTransaction>();

    private Integer transactionsCoinType;
    private Long idUser;

    private final ZonedDateTime register = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));

    public Transactions() {}

    public Transactions(Long idUser, CoinType transactionsCoinType) {
        this.idUser = idUser;
        setTransactionsCoinType(transactionsCoinType);
    }

    public void setTransactionsCoinType(CoinType transactionsCoinType) {
        if(transactionsCoinType != null) {
            this.transactionsCoinType = transactionsCoinType.getCode();
        }
    }
    public CoinType getTransactionsCoinType() {
        return CoinType.valueOf(transactionsCoinType);
    }

    public Long getId() {
        return id;
    }

    public List<CoinTransaction> getCoinTransactions() {
        return coinTransactions;
    }

    public Long getIdUser() {
        return idUser;
    }

    public ZonedDateTime getRegister() {
        return register;
    }
}
