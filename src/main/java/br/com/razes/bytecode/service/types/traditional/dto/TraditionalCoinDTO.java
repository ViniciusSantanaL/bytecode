package br.com.razes.bytecode.service.types.traditional.dto;

import br.com.razes.bytecode.service.api.layer.dto.ApiLayerResponseCoinsDTO;

import java.util.Map;

public class TraditionalCoinDTO {

    private Map<String, String> coinInfo;

    public TraditionalCoinDTO(ApiLayerResponseCoinsDTO apiCoinDetails) {
        this.coinInfo = apiCoinDetails.getSymbols();
    }

    public Map<String, String> getCoinInfo() {
        return coinInfo;
    }

    public void setCoinInfo(Map<String, String> coinInfo) {
        this.coinInfo = coinInfo;
    }
}
