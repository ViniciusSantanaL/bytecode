package br.com.razes.bytecode.controller.transactions;

import br.com.razes.bytecode.controller.trades.form.TradeForm;
import br.com.razes.bytecode.exception.ApiRequestException;
import br.com.razes.bytecode.model.coin.CoinType;
import br.com.razes.bytecode.model.rates.ExchangeRate;
import br.com.razes.bytecode.model.transactions.Transactions;
import br.com.razes.bytecode.model.transactions.dto.TradeDTO;
import br.com.razes.bytecode.model.transactions.dto.TransactionsDTO;
import br.com.razes.bytecode.model.wallet.Wallet;
import br.com.razes.bytecode.security.TokenService;
import br.com.razes.bytecode.service.rates.ExchangeRateService;
import br.com.razes.bytecode.service.transactions.TransactionsService;
import br.com.razes.bytecode.service.wallet.WalletService;
import br.com.razes.bytecode.utils.CalculateTradeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.net.URI;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private TransactionsService transactionsService;

    @Autowired
    private ExchangeRateService exchangeRateService;

    @Autowired
    private WalletService walletService;

    @PostMapping
    public ResponseEntity<?> doTransaction(@RequestBody @Valid TradeForm tradeForm,
         @RequestHeader(value = "Authorization") String token, UriComponentsBuilder uriBuilder) {
        ExchangeRate rates = exchangeRateService.getExchangeRateBySymbol(tradeForm.getBaseSymbol());

        if(rates == null)
            throw new ApiRequestException("This Base Symbol not Exist: " + tradeForm.getBaseSymbol());
        CoinType coinType = CoinType.valueOf(tradeForm.getType());

        BigDecimal result = CalculateTradeUtils.calculateTradeFromOneCoin(tradeForm.getFromSymbol(),tradeForm.getAmount(),rates);
        Long idUser = tokenService.getIdUser(token.substring(7));
        BigDecimal rate = result.divide(tradeForm.getAmount());
        TradeDTO tradeDTO =  new TradeDTO(result,rate,rates.getUpdateRate());

        Transactions transactions = transactionsService.getTransactionsBy(idUser,coinType);
        Wallet wallet = walletService.findByIdUser(idUser);
        transactionsService.saveTransactions(idUser,coinType,tradeForm,tradeDTO,transactions,wallet);

        URI uri = uriBuilder.path("/api/transaction/{type}").buildAndExpand(coinType.getCode()).toUri();

        return ResponseEntity.created(uri).body(tradeDTO);
    }
    @GetMapping
    public TransactionsDTO allUserTransaction(@RequestHeader(value = "Authorization") String token) {

        Long idUser = tokenService.getIdUser(token.substring(7));

        Transactions transactions = transactionsService.getTransactionsByIdUser(idUser);

        return TransactionsDTO.converter(transactions);
    }


    @GetMapping("/{type}")
    public ResponseEntity<TransactionsDTO> userTransactionByType(@RequestHeader(value = "Authorization") String token,
                                   @PathVariable(value = "type") int coinType) {

        Long idUser = tokenService.getIdUser(token.substring(7));
        CoinType type = CoinType.valueOf(coinType);
        Transactions transactions = transactionsService.getTransactionsBy(idUser,type);

        return ResponseEntity.ok(new TransactionsDTO(transactions));
    }
}
