package br.com.razes.bytecode.scheduler;

import br.com.razes.bytecode.model.coin.Coin;
import br.com.razes.bytecode.model.coin.CoinType;
import br.com.razes.bytecode.service.coin.CoinService;
import br.com.razes.bytecode.service.types.traditional.TraditionalCoinApiService;
import br.com.razes.bytecode.service.types.traditional.dto.TraditionalCoinDTO;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ScheduleTraditionalCoin {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleTraditionalCoin.class);

    @Autowired
    @Qualifier("ApiLayer")
    private TraditionalCoinApiService apiService;

    @Autowired
    private CoinService coinService;

    private void  createTraditionalCoinsIfHaveApiData() {
        try {
            TraditionalCoinDTO apiCoins = apiService.getAllCoinsDetails();

            createTraditionalCoinsIfHaveData(apiCoins.getCoinInfo());
        }catch (Exception e) {
            LOGGER.error("Error for Update Traditional Coins", e);
        }
        LOGGER.info("Success Update Traditional Coins");
    }
    private void createTraditionalCoinsIfHaveData(@NotNull Map<String, String> coinInfo) {
        List<Coin> newCoins = new ArrayList<Coin>();
        List<String> symbols = coinService.getAllSymbolsByType(CoinType.TRADITIONAL);

        for(Map.Entry<String, String> coin: coinInfo.entrySet()){
            if(!symbols.contains(coin.getKey())){
                Coin newCoin = createTraditionalCoin(coin.getKey(),coin.getValue());
                newCoins.add(newCoin);
            }
        }
        if(!newCoins.isEmpty()) {
            LOGGER.info("Starting Save All Coins");
            coinService.saveAllCoins(newCoins);
        }

    }

    private Coin createTraditionalCoin(String symbol, String name) {
        return new Coin(symbol,name,CoinType.TRADITIONAL);
    }


}
