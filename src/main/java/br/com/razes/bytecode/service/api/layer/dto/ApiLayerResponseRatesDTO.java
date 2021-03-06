package br.com.razes.bytecode.service.api.layer.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigInteger;
import java.util.Map;
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiLayerResponseRatesDTO {
    private String base;
    private String date;
    private Map<String, String> rates;
    private boolean success;


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Map<String, String> getRates() {
        return rates;
    }

    public void setRates(Map<String, String> rates) {
        this.rates = rates;
    }



}
