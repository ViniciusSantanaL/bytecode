package br.com.razes.bytecode.model.wallet.dto;

import br.com.razes.bytecode.model.wallet.Wallet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WalletDTO {

    private final String baseSymbolBalance;

    private final BigDecimal walletBalance;

    private final List<WalletFragmentDTO> walletFragments = new ArrayList<>();
    public WalletDTO(Wallet newWallet) {
        this.baseSymbolBalance = newWallet.getGeneralSymbolBalance();
        this.walletBalance = newWallet.getGeneralBalance();
        this.walletFragments.addAll(newWallet.getWalletFragments().stream().map(WalletFragmentDTO::new).collect(Collectors.toList()));
    }

    public String getBaseSymbolBalance() {
        return baseSymbolBalance;
    }

    public BigDecimal getWalletBalance() {
        return walletBalance;
    }

    public List<WalletFragmentDTO> getWalletFragments() {
        return walletFragments;
    }
}
