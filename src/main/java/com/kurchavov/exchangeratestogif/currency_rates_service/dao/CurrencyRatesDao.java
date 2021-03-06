package com.kurchavov.exchangeratestogif.currency_rates_service.dao;

import com.kurchavov.exchangeratestogif.currency_rates_service.model.CurrencyRates;
import com.kurchavov.exchangeratestogif.currency_rates_service.rest_service_client.CurrencyRateRestServiceClient;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.lang.reflect.InvocationTargetException;

// Класс доступа к данным курса валют

@Component
public class CurrencyRatesDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyRatesDao.class);

    // Метод возвращает объект с актуальными курсами валют

    public CurrencyRates.Rates getLatestRates(@NonNull CurrencyRateRestServiceClient currencyRateRestServiceClient,
                                              String apiKey, String baseCurrency) {
        return currencyRateRestServiceClient.getLatestRates(apiKey, baseCurrency).getRates();
    }

    // Метод возвращает объект с курсами валют за определенную дату

    public CurrencyRates.Rates getHistoricalRates(@NonNull CurrencyRateRestServiceClient currencyRateRestServiceClient,
                                                  String date, String apiKey, String baseCurrency ) {
        return currencyRateRestServiceClient.getHistoricalRates(date, apiKey, baseCurrency).getRates();
    }

    //Возвращает курс валюты в соответствии с ее трехзначным кодом

    public String getRate(@NonNull CurrencyRates.Rates rates, String currencyCode) {
        String rate = null;
        try {
            rate = (String) rates.getClass().getDeclaredMethod("get" + currencyCode).invoke(rates);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            LOGGER.error("Не удалось получить курс валюты!");
        }
        return rate;
    }
}
