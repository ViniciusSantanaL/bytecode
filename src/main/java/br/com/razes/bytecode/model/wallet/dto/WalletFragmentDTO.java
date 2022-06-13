package br.com.razes.bytecode.model.wallet.dto;

import br.com.razes.bytecode.model.wallet.WalletFragment;

import java.math.BigDecimal;

public class WalletFragmentDTO {

    private final Long id;

    private final String symbol;

    private final BigDecimal balance;

    public WalletFragmentDTO(WalletFragment walletFragment) {
        this.id = walletFragment.getId();
        this.symbol = walletFragment.getSymbol();
        this.balance = walletFragment.getBalance();
    }

    public Long getId() {
        return id;
    }

    public String getSymbol() {
        return symbol;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
