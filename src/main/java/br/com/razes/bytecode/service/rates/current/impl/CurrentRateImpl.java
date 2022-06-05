package br.com.razes.bytecode.service.rates.current.impl;

import br.com.razes.bytecode.model.rates.CurrentRate;
import br.com.razes.bytecode.repository.CurrentRateRepository;
import br.com.razes.bytecode.service.rates.current.CurrentRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrentRateImpl implements CurrentRateService {


    @Autowired
    private CurrentRateRepository currentRateRepository;

    @Override
    public void saveExchangeRate(CurrentRate currentRate) {
        currentRateRepository.save(currentRate);
    }

    @Override
    public void saveAllExchangeRates(List<CurrentRate> currentRates) {
        currentRateRepository.saveAll(currentRates);
    }
}
