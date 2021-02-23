package com.kurchavov.exchangeratestogif.currency_rates_service.rest_service_client;

import com.kurchavov.exchangeratestogif.currency_rates_service.model.CurrencyRates;


public interface CurrencyRateRestServiceClient {
    CurrencyRates getLatestRates(String apiKey, String baseCurrency);
    CurrencyRates getHistoricalRates(String date, String apiKey, String baseCurrency);
}
