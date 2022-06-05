package br.com.razes.bytecode.repository;

import br.com.razes.bytecode.model.coin.Coin;
import br.com.razes.bytecode.model.rates.ExchangeRate;
import feign.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {

    ExchangeRate findByBaseCoin(String baseCoin);

}
