package br.com.razes.bytecode.service.wallet;

import br.com.razes.bytecode.model.coin.Coin;
import br.com.razes.bytecode.model.coin.CoinType;
import br.com.razes.bytecode.model.wallet.Wallet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface WalletService {


    Wallet saveWallet(Wallet wallet);

    void saveAllWallets(List<Wallet> wallets);

    Wallet findByIdUser(Long idUser);

    String getBaseSymbolWalletByIdUser(Long idUser);


    Wallet changeBaseBalanceWallet(Wallet wallet, String symbol);

    Wallet deleteFragment(Wallet wallet, String symbolFragment);
}
