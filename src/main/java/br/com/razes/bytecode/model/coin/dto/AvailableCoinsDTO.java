package br.com.razes.bytecode.model.coin.dto;

public class AvailableCoinsDTO {
    private String symbol;

    public AvailableCoinsDTO(String symbol) {

        this.symbol = symbol;
    }


    public String getSymbol() {
        return symbol;
    }
}
