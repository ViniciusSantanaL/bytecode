package br.com.razes.bytecode.model.coin;

import java.util.Arrays;

public enum CoinType {

    TRADITIONAL(1),
    CRYPTO(2);

    private final int code;

    private CoinType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static CoinType valueOf(int codeCoinType) {
        for(CoinType value : CoinType.values()) {
                if(codeCoinType == value.getCode())
                    return value;
        }
        throw new IllegalArgumentException("Invalid CoinType code");
    }
}
