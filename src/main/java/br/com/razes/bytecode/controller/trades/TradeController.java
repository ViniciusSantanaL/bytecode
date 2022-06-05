package br.com.razes.bytecode.controller.trades;

import br.com.razes.bytecode.controller.trades.form.TradeForm;
import br.com.razes.bytecode.exception.ApiRequestException;
import br.com.razes.bytecode.model.coin.CoinType;
import br.com.razes.bytecode.model.rates.ExchangeRate;
import br.com.razes.bytecode.model.transactions.Transactions;
import br.com.razes.bytecode.model.transactions.dto.TradeDTO;
import br.com.razes.bytecode.security.TokenService;
import br.com.razes.bytecode.service.rates.ExchangeRateService;
import br.com.razes.bytecode.service.trades.TransactionsService;
import br.com.razes.bytecode.utils.CalculateTradeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/trade")
public class TradeController {

    @Autowired
    private ExchangeRateService exchangeRateService;

    @Autowired
    private TransactionsService transactionsService;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> trade(@RequestBody @Valid TradeForm tradeForm, @RequestHeader(value = "Authorization") String token) {

        ExchangeRate rates = exchangeRateService.getExchangeRateBySymbol(tradeForm.getBaseSymbol());

        if(rates == null)
            throw new ApiRequestException("This Base Symbol not Exist: " + tradeForm.getBaseSymbol());

        CoinType coinType = CoinType.valueOf(tradeForm.getType());

        BigDecimal result = CalculateTradeUtils.calculateTradeFromOneCoin(tradeForm,rates);
        Long idUser = tokenService.getIdUser(token.substring(7));
        BigDecimal rate = result.divide(tradeForm.getAmount());


        Transactions transactions = transactionsService.getTransactionsBy(idUser,coinType);


        return ResponseEntity.ok(new TradeDTO(result,rate,rates.getUpdateRate()));
    }
}
