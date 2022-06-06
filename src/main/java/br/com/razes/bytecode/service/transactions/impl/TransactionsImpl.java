package br.com.razes.bytecode.service.transactions.impl;

import br.com.razes.bytecode.controller.trades.form.TradeForm;
import br.com.razes.bytecode.model.coin.CoinType;
import br.com.razes.bytecode.model.transactions.Transactions;
import br.com.razes.bytecode.model.transactions.dto.TradeDTO;
import br.com.razes.bytecode.model.transactions.transaction.CoinTransaction;
import br.com.razes.bytecode.repository.TransactionsRepository;
import br.com.razes.bytecode.service.transactions.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionsImpl implements TransactionsService {

    @Autowired
    private TransactionsRepository transactionsRepository;

    @Override
    public Transactions getTransactionsBy(Long idUser, CoinType coinType) {
        return transactionsRepository.findByIdUserAndTransactionsCoinType(idUser,coinType.getCode());
    }

    @Override
    public Page<Transactions> getTransactionsByIdUser(Long idUser, Pageable pageable) {
        return transactionsRepository.findByIdUser(idUser,pageable);
    }

    @Override
    public void saveTransactions(Long idUser, CoinType coinType,
                 TradeForm tradeForm, TradeDTO tradeDTO, Transactions transactions) {

        if(transactions == null) {
            Transactions newTransactions = new Transactions(idUser,coinType);
            newTransactions.getCoinTransactions()
                    .add(createCoinTransaction(tradeForm,tradeDTO,newTransactions));
            transactionsRepository.save(newTransactions);
        }else {
            transactions.getCoinTransactions()
                    .add(createCoinTransaction(tradeForm,tradeDTO,transactions));
            transactionsRepository.save(transactions);
        }
    }

    private CoinTransaction createCoinTransaction(TradeForm tradeForm,TradeDTO tradeDTO,Transactions transactions) {
        BigDecimal amount = tradeForm.getAmount();
        String symbolFromCoin = tradeForm.getBaseSymbol();
        String symbolToCoin = tradeForm.getFromSymbol();
        BigDecimal result = tradeDTO.getResult();
        BigDecimal rate = tradeDTO.getRate();

        return new CoinTransaction(amount,symbolFromCoin,symbolToCoin,result,rate,transactions);
    }


}
