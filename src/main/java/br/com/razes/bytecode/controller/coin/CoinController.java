package br.com.razes.bytecode.controller.coin;

import br.com.razes.bytecode.model.coin.Coin;
import br.com.razes.bytecode.model.coin.CoinType;
import br.com.razes.bytecode.model.coin.dto.CoinDTO;
import br.com.razes.bytecode.service.coin.CoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@RestController
@RequestMapping("/api/coin")
public class CoinController {

    @Autowired
    private CoinService coinService;

    @GetMapping
    public Page<CoinDTO> listAllCoins(@RequestParam(required = false) @NotNull @Size(min = 3) @NotBlank @NotEmpty String symbols,
        @PageableDefault(sort="symbol", direction = Sort.Direction.ASC, page = 0, size=10) Pageable pagination){

        Page<Coin> coins;
        if(symbols != null)
            coins = coinService.findBySymbols(symbols.toUpperCase().split(","), pagination);
        else
            coins = coinService.listAllCoins(pagination);

        return CoinDTO.converterForPage(coins);
    }
}
