package br.com.razes.bytecode.service.trades.impl;

import br.com.razes.bytecode.model.coin.CoinType;
import br.com.razes.bytecode.model.transactions.Transactions;
import br.com.razes.bytecode.model.transactions.dto.TradeDTO;
import br.com.razes.bytecode.repository.TransactionsRepository;
import br.com.razes.bytecode.service.trades.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionsImpl implements TransactionsService {

    @Autowired
    private TransactionsRepository transactionsRepository;

    @Override
    public Transactions getTransactionsBy(Long idUser, CoinType coinType) {
        return transactionsRepository.findByIdUserAndTransactionsCoinType(idUser,coinType.getCode());
    }

    @Override
    public void saveTransactions(Long idUser, CoinType coinType, TradeDTO tradeDTO) {

    }
}
