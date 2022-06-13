package br.com.razes.bytecode.service.transactions;

import br.com.razes.bytecode.controller.trades.form.TradeForm;
import br.com.razes.bytecode.model.coin.CoinType;
import br.com.razes.bytecode.model.transactions.Transactions;
import br.com.razes.bytecode.model.transactions.dto.TradeDTO;
import br.com.razes.bytecode.model.wallet.Wallet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TransactionsService {

    Transactions getTransactionsBy(Long idUser, CoinType coinType);

    Page<Transactions> getTransactionsByIdUser(Long idUser, Pageable pageable);

    void saveTransactions(Long idUser, CoinType coinType, TradeForm tradeForm, TradeDTO tradeDTO, Transactions tran, Wallet Wallet);
}
