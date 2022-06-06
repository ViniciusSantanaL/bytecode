package br.com.razes.bytecode.model.transactions.dto;

import br.com.razes.bytecode.model.transactions.Transactions;
import org.springframework.data.domain.Page;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TransactionsDTO {

    private final int type;

    private final Set<CoinTransactionDTO> transactions;

    public TransactionsDTO(Transactions transactions) {
        this.type = transactions.getTransactionsCoinType().getCode();
        this.transactions = new HashSet<>();
        this.transactions.addAll(transactions.getCoinTransactions().stream().map(CoinTransactionDTO::new).collect(Collectors.toList()));

    }

    public int getType() {
        return type;
    }

    public Set<CoinTransactionDTO> getTransactions() {
        return transactions;
    }

    public static Page<TransactionsDTO> converter(Page<Transactions> transactions) {
        return transactions.map(TransactionsDTO::new);
    }
}
