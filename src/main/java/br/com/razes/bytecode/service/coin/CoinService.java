package br.com.razes.bytecode.service.coin;

import br.com.razes.bytecode.model.coin.Coin;
import br.com.razes.bytecode.model.coin.CoinType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CoinService {

    Page<Coin> listAllCoins(Pageable pageable);

    Page<Coin> findBySymbols(String[] symbol, Pageable pageable);

    List<String> getAllSymbolsByType(CoinType coinType);

    Coin saveCoin(Coin coin);

    List<Coin> saveAllCoins(List<Coin> coins);





}
