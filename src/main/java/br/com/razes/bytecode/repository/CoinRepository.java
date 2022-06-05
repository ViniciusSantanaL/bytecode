package br.com.razes.bytecode.repository;

import br.com.razes.bytecode.model.coin.Coin;
import feign.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface CoinRepository extends JpaRepository<Coin, Long> {

    @Query("select c from  Coin c where c.symbol in :symbols")
    Page<Coin> findBySymbols(@Param("symbols") String[] symbols, Pageable pageable);

    @Query("select c.symbol from Coin c where c.coinType = :coinType")
    Set<String> getAllSymbolsByType(Integer coinType);
}
