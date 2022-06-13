package br.com.razes.bytecode.service.wallet.impl;

import br.com.razes.bytecode.exception.ApiRequestException;
import br.com.razes.bytecode.model.rates.ExchangeRate;
import br.com.razes.bytecode.model.wallet.Wallet;
import br.com.razes.bytecode.model.wallet.WalletFragment;
import br.com.razes.bytecode.repository.WalletFragmentRepository;
import br.com.razes.bytecode.repository.WalletRepository;
import br.com.razes.bytecode.service.rates.ExchangeRateService;
import br.com.razes.bytecode.service.wallet.WalletService;
import br.com.razes.bytecode.utils.CalculateTradeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class WalletImpl implements WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private WalletFragmentRepository walletFragmentRepository;
    @Autowired
    private ExchangeRateService exchangeRateService;



    @Override
    public Wallet saveWallet(Wallet wallet) {
        return walletRepository.save(wallet);
    }

    @Override
    public void saveAllWallets(List<Wallet> wallets) {
        walletRepository.saveAll(wallets);
    }

    @Override
    public Wallet findByIdUser(Long idUser) {
        return walletRepository.findByIdUser(idUser);
    }

    @Override
    public Wallet changeBaseBalanceWallet(Wallet wallet, String symbol) {

        wallet.setGeneralSymbolBalance(symbol);

        if(wallet.getWalletFragments().size() == 0)
            return walletRepository.save(wallet);

        Optional<WalletFragment> initialFragment =  wallet.getWalletFragments().stream()
                .filter(fragment -> fragment.getSymbol().equals(symbol))
                .findFirst();

        if(initialFragment.isPresent()) {
            wallet.setGeneralBalance(initialFragment.get().getBalance());
        } else {
            wallet.setGeneralBalance(BigDecimal.ZERO);
        }
        ExchangeRate rates = exchangeRateService.getExchangeRateBySymbol(symbol);
        wallet.getWalletFragments().forEach(walletFragment -> {
            if(!walletFragment.getSymbol().equals(symbol)) {

                BigDecimal resultRate = CalculateTradeUtils.calculateTradeFromOneCoin(
                        walletFragment.getSymbol(),walletFragment.getBalance(),rates);

                BigDecimal finalResult = wallet.getGeneralBalance().add(resultRate);
                wallet.setGeneralBalance(finalResult);
            }
        });

        return walletRepository.save(wallet);
    }

    @Override
    public Wallet deleteFragment(Wallet wallet, String symbolFragment) {

        Optional<WalletFragment> walletFragment =  wallet.getWalletFragments().stream()
                .filter(fragment -> fragment.getSymbol().equals(symbolFragment))
                .findFirst();
        if(walletFragment.isPresent()) {

            if(walletFragment.get().getSymbol().equals(wallet.getGeneralSymbolBalance())){

                BigDecimal finalResult = wallet.getGeneralBalance().subtract(walletFragment.get().getBalance());
                wallet.setGeneralBalance(finalResult);
            } else{
                ExchangeRate rates = exchangeRateService.getExchangeRateBySymbol(wallet.getGeneralSymbolBalance());

                BigDecimal resultRate = CalculateTradeUtils.calculateTradeFromOneCoin(
                        walletFragment.get().getSymbol(),walletFragment.get().getBalance(),rates);

                BigDecimal finalResult = wallet.getGeneralBalance().subtract(resultRate);
                wallet.setGeneralBalance(finalResult);
            }
            wallet.getWalletFragments().remove(walletFragment.get());
            walletFragmentRepository.delete(walletFragment.get());


        } else {
            throw new ApiRequestException("This Fragment Not Exist in Wallet: " + symbolFragment);
        }
        return walletRepository.save(wallet);
    }
}
