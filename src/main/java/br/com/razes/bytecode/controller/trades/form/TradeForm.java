package br.com.razes.bytecode.controller.trades.form;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class TradeForm {

    @NotNull
    private BigDecimal amount;

    @NotNull @NotEmpty @NotBlank @Length(min = 3, max = 3)
    private String baseSymbol;

    @NotNull @NotEmpty @NotBlank @Length(min = 3, max = 3)
    private String fromSymbol;

    @NotNull
    private int type;

    public TradeForm(){

    }
    public BigDecimal getAmount() {
        return amount;
    }

    public String getBaseSymbol() {
        return baseSymbol;
    }

    public String getFromSymbol() {
        return fromSymbol;
    }

    public int getType() {
        return type;
    }
}
