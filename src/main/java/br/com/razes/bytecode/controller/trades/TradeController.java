package br.com.razes.bytecode.controller.trades;

import br.com.razes.bytecode.controller.trades.form.TradeForm;
import br.com.razes.bytecode.exception.ApiRequestException;
import br.com.razes.bytecode.model.rates.ExchangeRate;
import br.com.razes.bytecode.model.transactions.dto.TradeDTO;
import br.com.razes.bytecode.service.rates.ExchangeRateService;
import br.com.razes.bytecode.utils.CalculateTradeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/trade")
public class TradeController {

    @Autowired
    private ExchangeRateService exchangeRateService;

    @GetMapping
    public ResponseEntity<TradeDTO> trade(@RequestBody @Valid TradeForm tradeForm) {

        ExchangeRate rates = exchangeRateService.getExchangeRateBySymbol(tradeForm.getBaseSymbol());

        if(rates == null)
            throw new ApiRequestException("This Base Symbol not Exist: " + tradeForm.getBaseSymbol());

        BigDecimal result = CalculateTradeUtils.calculateTradeFromOneCoin(tradeForm.getFromSymbol(),
                false,tradeForm.getAmount(),rates);

        BigDecimal rate = result.divide(tradeForm.getAmount());
        TradeDTO tradeDTO =  new TradeDTO(result.toString(),rate.toString(),rates.getUpdateRate());

        return ResponseEntity.ok(tradeDTO);
    }

}
