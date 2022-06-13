package br.com.razes.bytecode.repository;

import br.com.razes.bytecode.model.wallet.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

    Wallet findByIdUser(Long idUser);

}
