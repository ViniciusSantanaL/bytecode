package br.com.razes.bytecode.repository;

import br.com.razes.bytecode.model.rates.CurrentRate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrentRateRepository extends JpaRepository<CurrentRate, Long> {

}
