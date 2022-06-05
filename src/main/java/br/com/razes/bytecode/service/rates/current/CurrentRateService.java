package br.com.razes.bytecode.service.rates.current;

import br.com.razes.bytecode.model.rates.CurrentRate;

import java.util.List;

public interface CurrentRateService {

    void saveExchangeRate(CurrentRate currentRate);

    void saveAllExchangeRates(List<CurrentRate> currentRates);
}
