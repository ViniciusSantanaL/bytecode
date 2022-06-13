package br.com.razes.bytecode.model.wallet;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class WalletFragment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "wallet_fragment_sequence")
    @SequenceGenerator(name = "wallet_fragment_sequence", sequenceName = "wallet_fragment_sequence", allocationSize = 1)
    private Long id;

    private String symbol;

    private BigDecimal balance;

    @ManyToOne
    private Wallet wallet;

    public WalletFragment(){

    }

    public WalletFragment(String symbol, BigDecimal balance, Wallet wallet) {
        this.symbol = symbol;
        this.balance = balance;
        this.wallet = wallet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
