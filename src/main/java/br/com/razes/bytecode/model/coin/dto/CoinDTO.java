package br.com.razes.bytecode.model.coin.dto;

import br.com.razes.bytecode.model.coin.Coin;
import org.springframework.data.domain.Page;

public class CoinDTO {

    private final Long id;
    private final String symbol;
    private final String name;

    public CoinDTO(Coin coin){
        this.id = coin.getId();
        this.symbol = coin.getSymbol();
        this.name = coin.getName();
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public static Page<CoinDTO> converterForPage(Page<Coin> coins) {
        return coins.map(CoinDTO::new);
    }

}
