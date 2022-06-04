package br.com.razes.bytecode.service.types.traditional;

import br.com.razes.bytecode.service.types.traditional.dto.RatesTraditionalCoinDTO;
import br.com.razes.bytecode.service.types.traditional.dto.TraditionalCoinDTO;

public interface TraditionalCoinApiService {


    RatesTraditionalCoinDTO getAllExchangeRateOfBaseCoin(String baseCoin);

    TraditionalCoinDTO getAllCoinsDetails();

}
