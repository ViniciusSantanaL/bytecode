package br.com.razes.bytecode.scheduler;

import br.com.razes.bytecode.model.rates.ExchangeRate;
import br.com.razes.bytecode.service.rates.ExchangeRateService;
import br.com.razes.bytecode.service.types.traditional.TraditionalCoinApiService;
import br.com.razes.bytecode.service.types.traditional.dto.RatesTraditionalCoinDTO;
import br.com.razes.bytecode.utils.FileHandlerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

@Component
public class ScheduleExchangeRate {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleExchangeRate.class);

    @Autowired
    @Qualifier("ApiLayer")
    private TraditionalCoinApiService apiService;

    @Autowired
    private ExchangeRateService exchangeRateService;


    @Scheduled(initialDelay = 15000L, fixedDelay = 60 * 60 * 1000L)
    public void  updateAllExchangeRatesTraditionalCoins() {
        try {
            Set<String> symbolsAvailable =  FileHandlerUtils.getAllSymbolsAvailable();
            symbolsAvailable.forEach(this::updateExchangeRateTraditionalCoin);

        }catch (Exception e) {
            LOGGER.error("Error for Update Exchange Rate Traditional Coin", e);
        }
        LOGGER.info("Success Update Exchange Rate Traditional Coin");
    }
    @Async
    public void updateExchangeRateTraditionalCoin(String symbol) {
        LOGGER.info("STARTING UPDATE CURRENT RATES OF " + symbol);
        RatesTraditionalCoinDTO rates =  apiService.getAllExchangeRateOfBaseCoin(symbol);
        ExchangeRate exchangeRateUpdate =  exchangeRateService.getExchangeRateBySymbol(symbol);
        exchangeRateUpdate.getCurrentRates().forEach(currentRate -> {
            String rate = rates.getRates().get(currentRate.getSymbol());
            currentRate.setRate(new BigDecimal(rate));
        });
        exchangeRateUpdate.setActive(true);
        exchangeRateUpdate.setUpdateRate(ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")));
        exchangeRateService.saveExchangeRate(exchangeRateUpdate);
        LOGGER.info("FINISH UPDATE CURRENT RATES OF " + symbol);
    }

}
