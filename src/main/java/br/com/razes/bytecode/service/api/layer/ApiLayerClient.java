package br.com.razes.bytecode.service.api.layer;

import br.com.razes.bytecode.service.api.layer.dto.ApiLayerResponseCoinsDTO;
import br.com.razes.bytecode.service.api.layer.dto.ApiLayerResponseRatesDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient( value="tradicionalCoinClient", url ="https://api.apilayer.com/exchangerates_data/" )
public interface ApiLayerClient {

    @RequestMapping(method = RequestMethod.GET, value ="latest")
    ApiLayerResponseRatesDTO getLatestExchangeRatesBy(@RequestParam(value = "base")  String base,
                                                      @RequestHeader("apikey") String apiKey);


    @RequestMapping(method = RequestMethod.GET, value ="symbols")
    ApiLayerResponseCoinsDTO getAllCoinsRegistered(@RequestHeader("apikey") String apiKey);
}
