package br.com.razes.bytecode.utils;

import br.com.razes.bytecode.controller.trades.form.TradeForm;
import br.com.razes.bytecode.exception.ApiRequestException;
import br.com.razes.bytecode.model.rates.CurrentRate;
import br.com.razes.bytecode.model.rates.ExchangeRate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

public class CalculateTradeUtils {

    public static BigDecimal calculateTradeFromOneCoin(String fromSymbol,BigDecimal amount, ExchangeRate rates) {

        Optional<CurrentRate> currentRate = rates.getCurrentRates().stream()
                .filter(current -> current.getSymbol().equals(fromSymbol))
                        .findFirst();

        if(currentRate.isEmpty())
            throw new ApiRequestException("This From Symbol not Exist: " + fromSymbol);

        BigDecimal currentRateCoin = currentRate.get().getRate();

        if(currentRateCoin.compareTo(BigDecimal.ONE) == 1) {
            return amount.multiply(currentRateCoin.setScale(2,  RoundingMode.HALF_UP)) ;
        } else {
            return amount.divide(currentRateCoin,2, RoundingMode.HALF_UP) ;
        }

    }
}
