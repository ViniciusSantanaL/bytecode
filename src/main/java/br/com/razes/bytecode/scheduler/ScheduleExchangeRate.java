package br.com.razes.bytecode.scheduler;

import br.com.razes.bytecode.service.types.traditional.TraditionalCoinApiService;
import br.com.razes.bytecode.service.types.traditional.dto.RatesTraditionalCoinDTO;
import br.com.razes.bytecode.utils.FileHandlerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScheduleExchangeRate {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleExchangeRate.class);

    @Autowired
    @Qualifier("ApiLayer")
    private TraditionalCoinApiService apiService;


    private void  updateExchangeRateTraditionalCoin() {
        try {
            List<String> traditionalCoinsAvailable = FileHandlerUtils.getAllSymbolsAvailable();
            if(!traditionalCoinsAvailable.isEmpty()){
                traditionalCoinsAvailable.forEach(symbol ->{
                    RatesTraditionalCoinDTO rates = apiService.getAllExchangeRateOfBaseCoin(symbol);
                });

            }

        }catch (Exception e) {
            LOGGER.error("Error for Update Exchange Rate Traditional Coin", e);
        }
        LOGGER.info("Success Update Exchange Rate Traditional Coin");
    }


}
