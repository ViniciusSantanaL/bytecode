package br.com.razes.bytecode.model.rates.dto;

import br.com.razes.bytecode.model.rates.CurrentRate;
import br.com.razes.bytecode.model.rates.ExchangeRate;

import java.util.*;
import java.util.stream.Collectors;

public class AllCurrentRateDTO {

    private final String baseSymbol;
    private List<CurrentRateDTO> coinsBase = new ArrayList<>();

    public AllCurrentRateDTO(ExchangeRate exchangeRate) {

        this.baseSymbol = exchangeRate.getBaseCoin();
        this.coinsBase.addAll(exchangeRate.getCurrentRates().stream()
                .map(CurrentRateDTO::new).collect(Collectors.toList()));

        this.coinsBase = coinsBase.stream().sorted(Comparator.comparing(CurrentRateDTO::getSymbol)).collect(Collectors.toList());



    }

    public String getBaseSymbol() {
        return baseSymbol;
    }

    public List<CurrentRateDTO> getCoinsBase() {
        return coinsBase;
    }
}
