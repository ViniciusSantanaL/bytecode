package br.com.razes.bytecode.utils;

import br.com.razes.bytecode.controller.trades.form.TradeForm;
import br.com.razes.bytecode.exception.ApiRequestException;
import br.com.razes.bytecode.model.rates.CurrentRate;
import br.com.razes.bytecode.model.rates.ExchangeRate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

public class CalculateTradeUtils {

    public static BigDecimal calculateTradeFromOneCoin(String fromSymbol,boolean fragment,BigDecimal amount, ExchangeRate rates) {

        Optional<CurrentRate> currentRate = rates.getCurrentRates().stream()
                .filter(current -> current.getSymbol().equals(fromSymbol))
                        .findFirst();

        if(currentRate.isEmpty())
            throw new ApiRequestException("This From Symbol not Exist: " + fromSymbol);

        BigDecimal currentRateCoin = new BigDecimal(currentRate.get().getRate());
        if(fragment) {
            return amount.divide(currentRateCoin,2,RoundingMode.HALF_EVEN) ;
        }
        return amount.multiply(currentRateCoin).setScale(2, RoundingMode.HALF_EVEN) ;

    }
}
