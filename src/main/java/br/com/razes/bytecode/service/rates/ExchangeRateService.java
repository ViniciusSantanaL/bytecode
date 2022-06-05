package br.com.razes.bytecode.service.rates;

import br.com.razes.bytecode.model.rates.ExchangeRate;

import java.util.List;

public interface ExchangeRateService {

    void saveExchangeRate(ExchangeRate rate);

    void saveAllExchangeRates(List<ExchangeRate> rates);

    ExchangeRate getExchangeRateBySymbol(String baseCoin);
}
