package br.com.razes.bytecode.service.rates.impl;

import br.com.razes.bytecode.model.coin.Coin;
import br.com.razes.bytecode.model.rates.ExchangeRate;
import br.com.razes.bytecode.repository.ExchangeRateRepository;
import br.com.razes.bytecode.service.rates.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExchangeRateImpl implements ExchangeRateService {


    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    @Override
    public void saveExchangeRate(ExchangeRate rate) {
        exchangeRateRepository.save(rate);
    }

    @Override
    public void saveAllExchangeRates(List<ExchangeRate> rates) {
        exchangeRateRepository.saveAll(rates);
    }

    @Override
    public ExchangeRate getExchangeRateBySymbol(String baseCoin) {
        return exchangeRateRepository.findByBaseCoin(baseCoin);
    }

}
