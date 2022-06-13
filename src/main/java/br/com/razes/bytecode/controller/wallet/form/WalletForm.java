package br.com.razes.bytecode.controller.wallet.form;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class WalletForm {


    @NotNull @NotEmpty @NotBlank
    private String symbolBalance;

    @NotNull @NotEmpty @NotBlank
    private String initialBalance;

    public WalletForm() {

    }
    public String getSymbolBalance() {
        return symbolBalance;
    }

    public String getInitialBalance() {
        return initialBalance;
    }
}
