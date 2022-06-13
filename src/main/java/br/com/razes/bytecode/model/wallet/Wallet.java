package br.com.razes.bytecode.model.wallet;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "wallet_sequence")
    @SequenceGenerator(name = "wallet_sequence", sequenceName = "wallet_sequence", allocationSize = 1)
    private Long id;

    private BigDecimal generalBalance = BigDecimal.ZERO;

    private String generalSymbolBalance;

    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<WalletFragment> walletFragments = new ArrayList<WalletFragment>();

    private Long idUser;

    public Wallet() {

    }

    public Wallet(BigDecimal generalBalance, String generalSymbolBalance, Long idUser) {

        this.generalBalance = generalBalance;
        this.generalSymbolBalance = generalSymbolBalance;
        this.idUser = idUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getGeneralBalance() {
        return generalBalance;
    }

    public void setGeneralBalance(BigDecimal generalBalance) {
        this.generalBalance = generalBalance;
    }

    public String getGeneralSymbolBalance() {
        return generalSymbolBalance;
    }

    public void setGeneralSymbolBalance(String generalSymbolBalance) {
        this.generalSymbolBalance = generalSymbolBalance;
    }

    public List<WalletFragment> getWalletFragments() {
        return walletFragments;
    }

    public void setWalletFragments(List<WalletFragment> walletFragments) {
        this.walletFragments = walletFragments;
    }
}
