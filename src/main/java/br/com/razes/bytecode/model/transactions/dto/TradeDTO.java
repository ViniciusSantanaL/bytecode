package br.com.razes.bytecode.model.transactions.dto;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class TradeDTO {


    private String result;

    private  String rate;

    private ZonedDateTime timeRate;

    public TradeDTO(String result, String rate, ZonedDateTime timeRate) {
        this.result = result;
        this.rate = rate;
        this.timeRate = timeRate;
    }

    public String getResult() {
        return result;
    }

    public String getRate() {
        return rate;
    }

    public ZonedDateTime getTimeRate() {
        return timeRate;
    }
}
