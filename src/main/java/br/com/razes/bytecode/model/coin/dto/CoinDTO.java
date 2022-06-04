package br.com.razes.bytecode.model.coin.dto;

import br.com.razes.bytecode.model.coin.Coin;
import org.springframework.data.domain.Page;

public class CoinDTO {

    private final String symbol;
    private final String name;

    public CoinDTO(Coin coin){
        this.symbol = coin.getSymbol();
        this.name = coin.getName();
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public static Page<CoinDTO> converterForPage(Page<Coin> coins) {
        return coins.map(CoinDTO::new);
    }

}
