package br.com.razes.bytecode.service.coin.impl;

import br.com.razes.bytecode.model.coin.Coin;
import br.com.razes.bytecode.model.coin.CoinType;
import br.com.razes.bytecode.repository.CoinRepository;
import br.com.razes.bytecode.service.coin.CoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoinImpl implements CoinService {

    @Autowired
    private CoinRepository coinRepository;
    public Page<Coin> listAllCoins(Pageable pageable) {
        return coinRepository.findAll(pageable);
    }

    @Override
    public Page<Coin> findBySymbols(String[] symbols, Pageable pageable) {
        return coinRepository.findBySymbols(symbols,pageable);
    }

    @Override
    public List<String> getAllSymbolsByType(CoinType coinType) {
        return coinRepository.getAllSymbolsByType(coinType.getCode());
    }

    @Override
    public Coin saveCoin(Coin newCoin) {
        return coinRepository.save(newCoin);
    }

    @Override
    public List<Coin> saveAllCoins(List<Coin> coins) {
        coins =  coinRepository.saveAll(coins);

        return coins;
    }


}
