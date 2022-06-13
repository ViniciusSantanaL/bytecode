package br.com.razes.bytecode.controller.rates;

import br.com.razes.bytecode.exception.ApiRequestException;
import br.com.razes.bytecode.model.rates.CurrentRate;
import br.com.razes.bytecode.model.rates.ExchangeRate;
import br.com.razes.bytecode.model.rates.dto.AllCurrentRateDTO;
import br.com.razes.bytecode.model.rates.dto.CurrentRateDTO;
import br.com.razes.bytecode.service.rates.ExchangeRateService;
import feign.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/rate")
public class ExchangeRatesController {

    @Autowired
    private ExchangeRateService exchangeRateService;

    @GetMapping
    public ResponseEntity<CurrentRateDTO> exchangeRateOneCoin(@RequestParam String base, @RequestParam String to) {
        ExchangeRate rates = exchangeRateService.getExchangeRateBySymbol(base);

        if(rates == null)
            throw new ApiRequestException("This Base Symbol not Exist: " + base);

        Optional<CurrentRate> currentRate =  rates.getCurrentRates().stream().
                filter(current -> current.getSymbol().equals(to)).findFirst();
        if(currentRate.isEmpty())
            throw new ApiRequestException("This From Symbol not Exist: " + to);

        return ResponseEntity.ok(new CurrentRateDTO(currentRate.get()));
    }
    @GetMapping("/all")
    public ResponseEntity<AllCurrentRateDTO> exchangeRateAll(@RequestParam String base) {

        ExchangeRate rates = exchangeRateService.getExchangeRateBySymbol(base);

        if(rates == null)
            throw new ApiRequestException("This Base Symbol not Exist: " + base);


        return ResponseEntity.ok(new AllCurrentRateDTO(rates));
    }

}
