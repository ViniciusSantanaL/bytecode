package br.com.razes.bytecode.service.transactions.impl;

import br.com.razes.bytecode.controller.trades.form.TradeForm;
import br.com.razes.bytecode.model.coin.CoinType;
import br.com.razes.bytecode.model.transactions.Transactions;
import br.com.razes.bytecode.model.transactions.dto.TradeDTO;
import br.com.razes.bytecode.model.transactions.transaction.CoinTransaction;
import br.com.razes.bytecode.model.wallet.Wallet;
import br.com.razes.bytecode.model.wallet.WalletFragment;
import br.com.razes.bytecode.repository.TransactionsRepository;
import br.com.razes.bytecode.service.transactions.TransactionsService;
import br.com.razes.bytecode.service.wallet.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class TransactionsImpl implements TransactionsService {

    @Autowired
    private TransactionsRepository transactionsRepository;

    @Autowired
    private WalletService walletService;

    @Override
    public Transactions getTransactionsBy(Long idUser, CoinType coinType) {
        return transactionsRepository.findByIdUserAndTransactionsCoinType(idUser,coinType.getCode());
    }

    @Override
    public Transactions getTransactionsByIdUser(Long idUser) {
        return transactionsRepository.findByIdUser(idUser);
    }

    @Override
    public void saveTransactions(Long idUser, CoinType coinType,
                 TradeForm tradeForm, TradeDTO tradeDTO, Transactions transactions, Wallet wallet) {

        if(transactions == null) {
            Transactions newTransactions = new Transactions(idUser,coinType, wallet.getId());
            CoinTransaction coinTransaction = createCoinTransaction(tradeForm,tradeDTO,newTransactions);
            BigDecimal addResultWallet = coinTransaction.getResult();

            wallet.setGeneralBalance(wallet.getGeneralBalance().add(addResultWallet));

            Optional<WalletFragment> walletFragment =  wallet.getWalletFragments().stream()
                    .filter(fragment -> fragment.getSymbol().equals(coinTransaction.getSymbolToCoin()))
                    .findFirst();
            newTransactions.getCoinTransactions()
                    .add(coinTransaction);

            if(walletFragment.isPresent()) {
                wallet.getWalletFragments().remove(walletFragment.get());
                BigDecimal result = walletFragment.get().getBalance().add(coinTransaction.getAmount());
                walletFragment.get().setBalance(result);
                wallet.getWalletFragments().add(walletFragment.get());

            }else {
                WalletFragment newFragment = new WalletFragment(
                        coinTransaction.getSymbolToCoin(),coinTransaction.getAmount(),wallet);
                wallet.getWalletFragments().add(newFragment);
            }
            walletService.saveWallet(wallet);
            transactionsRepository.save(newTransactions);
        }else {
            CoinTransaction coinTransaction = createCoinTransaction(tradeForm,tradeDTO,transactions);

            BigDecimal addResultWallet = coinTransaction.getResult();
            wallet.setGeneralBalance(wallet.getGeneralBalance().add(addResultWallet));

            Optional<WalletFragment> walletFragment =  wallet.getWalletFragments().stream()
                    .filter(fragment -> fragment.getSymbol().equals(coinTransaction.getSymbolToCoin()))
                    .findFirst();

            transactions.getCoinTransactions()
                    .add(createCoinTransaction(tradeForm,tradeDTO,transactions));

            if(walletFragment.isPresent()) {
                wallet.getWalletFragments().remove(walletFragment.get());
                BigDecimal result = walletFragment.get().getBalance().add(coinTransaction.getAmount());
                walletFragment.get().setBalance(result);
                wallet.getWalletFragments().add(walletFragment.get());

            }else {
                WalletFragment newFragment = new WalletFragment(
                        coinTransaction.getSymbolToCoin(),coinTransaction.getAmount(),wallet);
                wallet.getWalletFragments().add(newFragment);
            }

            walletService.saveWallet(wallet);
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
