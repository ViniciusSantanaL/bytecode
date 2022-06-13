package br.com.razes.bytecode.controller.wallet;

import br.com.razes.bytecode.controller.wallet.form.WalletForm;
import br.com.razes.bytecode.controller.wallet.form.WalletFragmentFrom;
import br.com.razes.bytecode.exception.ApiRequestException;
import br.com.razes.bytecode.model.coin.CoinType;
import br.com.razes.bytecode.model.rates.ExchangeRate;
import br.com.razes.bytecode.model.wallet.Wallet;
import br.com.razes.bytecode.model.wallet.WalletFragment;
import br.com.razes.bytecode.model.wallet.dto.WalletDTO;
import br.com.razes.bytecode.security.TokenService;
import br.com.razes.bytecode.service.coin.CoinService;
import br.com.razes.bytecode.service.rates.ExchangeRateService;
import br.com.razes.bytecode.service.wallet.WalletService;
import br.com.razes.bytecode.utils.CalculateTradeUtils;
import br.com.razes.bytecode.utils.FileHandlerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ExchangeRateService exchangeRateService;

    @Autowired
    private CoinService coinService;

    @PostMapping
    public ResponseEntity<WalletDTO> createWallet(@RequestBody @Valid  WalletForm walletForm,
        @RequestHeader(value = "Authorization") String token, UriComponentsBuilder uriBuilder) {

        Set<String> availableCoins = FileHandlerUtils.getAllSymbolsAvailable();

        if(!availableCoins.contains(walletForm.getSymbolBalance()))
            throw new ApiRequestException("This Base Symbol not Exist: " + walletForm.getSymbolBalance());


        Long idUser = tokenService.getIdUser(token.substring(7));

        if(walletService.findByIdUser(idUser) != null)
            throw new ApiRequestException("This Wallet for this user Already Exists : idUser(" + idUser + ")");

        Wallet newWallet  = new Wallet(new BigDecimal(walletForm.getInitialBalance()), walletForm.getSymbolBalance(), idUser);

        newWallet = walletService.saveWallet(newWallet);

        URI uri = uriBuilder.path("/api/wallet/{idUser}").buildAndExpand(idUser).toUri();

        return ResponseEntity.created(uri).body(new WalletDTO(newWallet));

    }

    @GetMapping
    public ResponseEntity<WalletDTO> getWalletByUserId(@RequestHeader(value = "Authorization") String token) {

        Long idUser = tokenService.getIdUser(token.substring(7));

        Wallet wallet = walletService.findByIdUser(idUser);

        if(wallet == null)
            throw new ApiRequestException("This wallet not Exist for this User: idUser(" + idUser + ")");

        return ResponseEntity.ok(new WalletDTO(wallet));
    }

    @PostMapping("/add-fragment")
    public ResponseEntity<WalletDTO> addFragmentWallet(@RequestBody @Valid WalletFragmentFrom walletFragmentFrom,
        @RequestHeader(value = "Authorization") String token, UriComponentsBuilder uriBuilder) {

        Set<String> symbols =  coinService.getAllSymbolsByType(CoinType.TRADITIONAL);

        if(!symbols.contains(walletFragmentFrom.getSymbol()))
            throw new ApiRequestException("This Base Symbol not Exist: " + walletFragmentFrom.getSymbol());

        Long idUser = tokenService.getIdUser(token.substring(7));

        Wallet wallet = walletService.findByIdUser(idUser);
        List<WalletFragment> fragments = wallet.getWalletFragments().stream()
                .filter(walletFragment -> walletFragment.getSymbol().equals(walletFragmentFrom.getSymbol()))
                .collect(Collectors.toList());

        if(fragments.size() > 0)
            throw new ApiRequestException("This Fragment already exists: " + walletFragmentFrom.getSymbol());

        WalletFragment newFragment = new WalletFragment(
                walletFragmentFrom.getSymbol(),new BigDecimal(walletFragmentFrom.getBalance()),wallet);


        wallet.getWalletFragments().add(newFragment);

        if(newFragment.getSymbol().equals(wallet.getGeneralSymbolBalance())){
            wallet.setGeneralBalance(wallet.getGeneralBalance().add(newFragment.getBalance()));

        }else {
            ExchangeRate rates = exchangeRateService.getExchangeRateBySymbol(wallet.getGeneralSymbolBalance());

            BigDecimal newBalanceFragmentRated = CalculateTradeUtils.calculateTradeFromOneCoin(
                    newFragment.getSymbol(),newFragment.getBalance(),rates);

            wallet.setGeneralBalance(wallet.getGeneralBalance().add(newBalanceFragmentRated));
        }


        walletService.saveWallet(wallet);

        URI uri = uriBuilder.path("/api/wallet/{idUser}").buildAndExpand(idUser).toUri();

        return ResponseEntity.created(uri).body(new WalletDTO(wallet));
    }
}
