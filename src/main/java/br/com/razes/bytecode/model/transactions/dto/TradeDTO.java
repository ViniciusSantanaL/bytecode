package br.com.razes.bytecode.model.transactions.dto;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class TradeDTO {


    private BigDecimal result;

    private  BigDecimal rate;

    private ZonedDateTime timeRate;

    public TradeDTO(BigDecimal result, BigDecimal rate, ZonedDateTime timeRate) {
        this.result = result;
        this.rate = rate;
        this.timeRate = timeRate;
    }

    public BigDecimal getResult() {
        return result;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public ZonedDateTime getTimeRate() {
        return timeRate;
    }
}
