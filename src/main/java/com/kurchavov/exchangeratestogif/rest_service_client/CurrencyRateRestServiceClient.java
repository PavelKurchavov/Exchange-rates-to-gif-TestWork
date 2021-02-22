package com.kurchavov.exchangeratestogif.rest_service_client;

import com.kurchavov.exchangeratestogif.model.CurrencyRates;


public interface CurrencyRateRestServiceClient {
    CurrencyRates getLatestRates(String apiKey, String baseCurrency);
    CurrencyRates getHistoricalRates(String date, String apiKey, String baseCurrency);
}
