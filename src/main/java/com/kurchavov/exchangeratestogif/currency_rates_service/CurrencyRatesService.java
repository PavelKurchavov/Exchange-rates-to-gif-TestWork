package com.kurchavov.exchangeratestogif.currency_rates_service;

import com.kurchavov.exchangeratestogif.currency_rates_service.dao.CurrencyRatesDao;
import com.kurchavov.exchangeratestogif.currency_rates_service.model.CurrencyRates;
import com.kurchavov.exchangeratestogif.currency_rates_service.rest_service_client.CurrencyRateRestServiceClient;
import com.kurchavov.exchangeratestogif.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

// Сервис, предоставляющий данные о курсах валют

@Service
@RequiredArgsConstructor
public class CurrencyRatesService {
    private final CurrencyRateRestServiceClient client;
    private final CurrencyRatesDao dao;

    @Value("${oxr.apiKey}")
    private String apiKey;

    @Value("${oxr.base}")
    private String baseCurrency;

    @Value("${oxr.daysBefore}")
    private int daysBefore;

    // Возвращает результат сравнения текущего и предыдущего курса нужной валюты

    public double getExchangeRate(String currencyCode) {
        CurrencyRates.Rates latestRates = dao.getLatestRates(client, apiKey, baseCurrency);
        CurrencyRates.Rates historicalRates = dao.getHistoricalRates(client, Util.getPreviousDate(daysBefore), apiKey, baseCurrency);
        double latestRateOfCurrency = Double.parseDouble(dao.getRate(latestRates, currencyCode));
        double historicalRateOfCurrency = Double.parseDouble(dao.getRate(historicalRates, currencyCode));
        return latestRateOfCurrency - historicalRateOfCurrency;
    }
}
