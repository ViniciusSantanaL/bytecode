package br.com.razes.bytecode.service.wallet.impl;

import br.com.razes.bytecode.model.wallet.Wallet;
import br.com.razes.bytecode.repository.WalletRepository;
import br.com.razes.bytecode.service.wallet.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletImpl implements WalletService {

    @Autowired
    private WalletRepository walletRepository;
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
}
