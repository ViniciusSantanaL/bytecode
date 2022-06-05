package br.com.razes.bytecode.service.types.traditional.dto;

import br.com.razes.bytecode.service.api.layer.dto.ApiLayerResponseRatesDTO;

import java.math.BigInteger;
import java.util.Map;

public class RatesTraditionalCoinDTO {

    private final String baseCoin;

    private final String date;

    private final Map<String, String> rates;

    public RatesTraditionalCoinDTO(ApiLayerResponseRatesDTO apiResponse) {

        this.baseCoin = apiResponse.getBase();
        this.date = apiResponse.getDate();
        this.rates = apiResponse.getRates();

    }

    public String getBaseCoin() {
        return baseCoin;
    }

    public String getDate() {
        return date;
    }

    public Map<String, String> getRates() {
        return rates;
    }
}
