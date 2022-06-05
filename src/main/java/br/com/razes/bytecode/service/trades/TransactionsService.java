package br.com.razes.bytecode.service.trades;

import br.com.razes.bytecode.model.coin.CoinType;
import br.com.razes.bytecode.model.transactions.Transactions;
import br.com.razes.bytecode.model.transactions.dto.TradeDTO;

public interface TransactionsService {

    Transactions getTransactionsBy(Long idUser, CoinType coinType);

    void saveTransactions(Long idUser, CoinType coinType, TradeDTO tradeDTO);
}
