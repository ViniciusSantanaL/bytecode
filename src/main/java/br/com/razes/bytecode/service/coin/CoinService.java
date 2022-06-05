package br.com.razes.bytecode.service.coin;

import br.com.razes.bytecode.model.coin.Coin;
import br.com.razes.bytecode.model.coin.CoinType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface CoinService {

    Page<Coin> listAllCoins(Pageable pageable);

    Page<Coin> findBySymbols(String[] symbol, Pageable pageable);

    Set<String> getAllSymbolsByType(CoinType coinType);

    void saveCoin(Coin coin);

    void saveAllCoins(List<Coin> coins);





}
