package br.com.razes.bytecode.scheduler;

import br.com.razes.bytecode.model.coin.Coin;
import br.com.razes.bytecode.model.coin.CoinType;
import br.com.razes.bytecode.model.rates.CurrentRate;
import br.com.razes.bytecode.model.rates.ExchangeRate;
import br.com.razes.bytecode.service.coin.CoinService;
import br.com.razes.bytecode.service.rates.ExchangeRateService;
import br.com.razes.bytecode.service.types.traditional.TraditionalCoinApiService;
import br.com.razes.bytecode.service.types.traditional.dto.TraditionalCoinDTO;
import br.com.razes.bytecode.utils.FileHandlerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Component
public class ScheduleTraditionalCoin {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleTraditionalCoin.class);

    @Autowired
    @Qualifier("ApiLayer")
    private TraditionalCoinApiService apiService;

    @Autowired
    private CoinService coinService;

    @Autowired
    private ExchangeRateService exchangeRateService;



    private void  createTraditionalCoinsIfHaveApiData() {
        try {
            TraditionalCoinDTO apiCoins = apiService.getAllCoinsDetails();
            Set<String> symbols = coinService.getAllSymbolsByType(CoinType.TRADITIONAL);
            createTraditionalCoinsIfNoHaveData(apiCoins.getCoinInfo(),symbols);
            createExchangeRateCoinIfNoHaveData(apiCoins.getCoinInfo().keySet(),symbols);

        }catch (Exception e) {
            LOGGER.error("Error for Update Traditional Coins", e);
        }
        LOGGER.info("Success Update Traditional Coins");
    }
    private void createTraditionalCoinsIfNoHaveData(Map<String, String> coinInfoApi, Set<String> symbolsData) {
        List<Coin> newCoins = new ArrayList<Coin>();

        for(Map.Entry<String, String> coin: coinInfoApi.entrySet()){
            if(!symbolsData.contains(coin.getKey())){
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

    private void createExchangeRateCoinIfNoHaveData(Set<String> symbolsApi, Set<String> symbolsData) throws IOException {
        Set<String> symbolsAvailable =  FileHandlerUtils.getAllSymbolsAvailable();
        List<ExchangeRate> newExchangeRates = new ArrayList<ExchangeRate>();

        symbolsApi.forEach(symbol -> {
            if(symbolsAvailable.contains(symbol) && !symbolsData.contains(symbol))
                newExchangeRates.add(createExchangeRateCoin(symbol,symbolsApi));
        });

        if(!newExchangeRates.isEmpty()){
             exchangeRateService.saveAllExchangeRates(newExchangeRates);

        }

    }

    private ExchangeRate createExchangeRateCoin(String symbol,Set<String> symbolsApi) {
        Set<String> symbolsApiFilterOne = new HashSet<>(symbolsApi);
        symbolsApiFilterOne.remove(symbol);
        ExchangeRate newExchangeRate = new ExchangeRate(symbol);
        symbolsApiFilterOne.forEach(symbolApi -> {
            newExchangeRate.getCurrentRates()
                    .add(createAllBaseCurrentRate(symbolApi, newExchangeRate));
        });

        return newExchangeRate;
    }
    private CurrentRate createAllBaseCurrentRate(String symbol, ExchangeRate rates) {
        return new CurrentRate(symbol,rates);
    }


}
