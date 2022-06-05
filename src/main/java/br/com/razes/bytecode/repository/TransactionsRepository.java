package br.com.razes.bytecode.repository;

import br.com.razes.bytecode.model.transactions.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionsRepository extends JpaRepository<Transactions, Long> {

    Transactions findByIdUserAndTransactionsCoinType(Long idUser, Integer transactionsCoinType);
}
