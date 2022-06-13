package br.com.razes.bytecode.controller.wallet.form;

import br.com.razes.bytecode.model.wallet.WalletFragment;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class WalletFragmentFrom {

    @NotNull
    @NotEmpty
    @NotBlank
    private String symbol;

    @NotNull
    @NotEmpty
    @NotBlank
    private String balance;

    public WalletFragmentFrom(){
    }

    public String getSymbol() {
        return symbol;
    }

    public String getBalance() {
        return balance;
    }
}
