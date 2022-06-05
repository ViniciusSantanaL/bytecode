package br.com.razes.bytecode.service.api.layer.impl;

import br.com.razes.bytecode.model.rates.ExchangeRate;
import br.com.razes.bytecode.service.api.layer.ApiLayerClient;
import br.com.razes.bytecode.service.api.layer.dto.ApiLayerResponseCoinsDTO;
import br.com.razes.bytecode.service.api.layer.dto.ApiLayerResponseRatesDTO;
import br.com.razes.bytecode.service.types.traditional.TraditionalCoinApiService;
import br.com.razes.bytecode.service.types.traditional.dto.RatesTraditionalCoinDTO;
import br.com.razes.bytecode.service.types.traditional.dto.TraditionalCoinDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Qualifier("ApiLayer")
public class ApiLayerImpl implements TraditionalCoinApiService {

    @Value(value = "${exchange.rate.api.key}")
    private String apiKey;
    @Autowired
    private ApiLayerClient apiLayerClient;

    @Override
    public RatesTraditionalCoinDTO getAllExchangeRateOfBaseCoin(String baseCoin) {

        ApiLayerResponseRatesDTO response = apiLayerClient.getLatestExchangeRatesBy(baseCoin,apiKey);

        if(response != null && !response.isSuccess())
            throw new RuntimeException("Api Layer not response for get All Exchange Rate of Base Coin");

        if(response != null)
            return new RatesTraditionalCoinDTO(response);

        throw new RuntimeException("Error for get All Exchange Rate of Base Coin");
    }

    @Override
    public TraditionalCoinDTO  getAllCoinsDetails() {

        ApiLayerResponseCoinsDTO response = apiLayerClient.getAllCoinsRegistered(apiKey);

        if(response != null && !response.isSuccess())
            throw new RuntimeException("Api Layer not response for get All Coins Details");

        if(response != null)
            return new TraditionalCoinDTO(response);

        throw new RuntimeException("Error for get All Coins Details");

    }

}
