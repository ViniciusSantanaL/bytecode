package br.com.razes.bytecode.repository;

import br.com.razes.bytecode.model.wallet.Wallet;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

    Wallet findByIdUser(Long idUser);

    @Query("select w.generalSymbolBalance from Wallet w where w.idUser = :idUser")
    String getSymbolBalanceWalletByIdUser(@Param("idUser") Long idUser);
}
