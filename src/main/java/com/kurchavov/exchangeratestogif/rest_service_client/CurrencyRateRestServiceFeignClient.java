package com.kurchavov.exchangeratestogif.rest_service_client;

import com.kurchavov.exchangeratestogif.model.CurrencyRates;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "oxr", url = "https://openexchangerates.org/api")
public interface CurrencyRateRestServiceFeignClient extends CurrencyRateRestServiceClient {

    @Override
    @GetMapping("/latest.json?app_id={apiKey}&base={base}")
    CurrencyRates getLatestRates(@PathVariable("apiKey") String apiKey, @PathVariable("base") String baseCurrency);

    @Override
    @GetMapping("/historical/{date}.json?app_id={apiKey}&base={base}")
    CurrencyRates getHistoricalRates(@PathVariable("date") String date, @PathVariable("apiKey") String apiKey, @PathVariable("base") String baseCurrency);
}
