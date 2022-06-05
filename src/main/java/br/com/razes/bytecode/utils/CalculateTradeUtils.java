package br.com.razes.bytecode.utils;

import br.com.razes.bytecode.controller.trades.form.TradeForm;
import br.com.razes.bytecode.exception.ApiRequestException;
import br.com.razes.bytecode.model.rates.CurrentRate;
import br.com.razes.bytecode.model.rates.ExchangeRate;

import java.math.BigDecimal;
import java.util.Optional;

public class CalculateTradeUtils {

    public static BigDecimal calculateTradeFromOneCoin(TradeForm tradeForm, ExchangeRate rates) {

        Optional<CurrentRate> currentRate = rates.getCurrentRates().stream()
                .filter(current -> current.getSymbol().equals(tradeForm.getFromSymbol()))
                        .findFirst();

        if(currentRate.isEmpty())
            throw new ApiRequestException("This From Symbol not Exist: " + tradeForm.getFromSymbol());

        BigDecimal currentRateCoin = currentRate.get().getRate();

        return tradeForm.getAmount().multiply(currentRateCoin) ;
    }
}
