package br.com.razes.bytecode.model.coin;

import javax.persistence.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;

@Entity
public class Coin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "coin_sequence")
    @SequenceGenerator(name = "coin_sequence", sequenceName = "coin_sequence", allocationSize = 1)
    private Long id;

    private String symbol;

    private String name;

    private Integer coinType;

    private final ZonedDateTime register = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));

    public Coin() {
    }

    public Coin(String symbol, String name, CoinType coinType) {
        this.symbol = symbol;
        this.name = name;
        setCoinType(coinType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coin coin = (Coin) o;
        return id.equals(coin.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CoinType getCoinType() {
        return CoinType.valueOf(coinType);
    }

    public void setCoinType(CoinType coinType) {
        if(coinType != null) {
            this.coinType = coinType.getCode();
        }
    }

    public ZonedDateTime getRegister() {
        return register;
    }
}
