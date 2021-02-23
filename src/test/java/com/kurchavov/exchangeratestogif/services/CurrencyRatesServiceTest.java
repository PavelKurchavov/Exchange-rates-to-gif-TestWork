package com.kurchavov.exchangeratestogif.services;

import com.kurchavov.exchangeratestogif.currency_rates_service.CurrencyRatesService;
import com.kurchavov.exchangeratestogif.currency_rates_service.dao.CurrencyRatesDao;
import com.kurchavov.exchangeratestogif.currency_rates_service.model.CurrencyRates;
import com.kurchavov.exchangeratestogif.currency_rates_service.rest_service_client.CurrencyRateRestServiceFeignClient;
import com.kurchavov.exchangeratestogif.util.Util;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;


@SpringBootTest
@EnableConfigurationProperties
@TestPropertySource("classpath:application-test.properties")
class CurrencyRatesServiceTest {

    @Value("${oxr.apiKey}")
    String apiKey;

    @Value("${oxr.base}")
    String base;

    @Value(("${oxr.daysBefore}"))
    int daysBefore;

    @MockBean
    CurrencyRateRestServiceFeignClient client;

    @MockBean
    CurrencyRatesDao dao;

    @Autowired
    CurrencyRatesService service;

    CurrencyRates.Rates latest;
    CurrencyRates.Rates historical;

    String date;


    @BeforeEach
    void setUp() {
        date = Util.getPreviousDate(daysBefore);
        latest = new CurrencyRates.Rates("0.4", "0.4", "105.5215", "74.035");
        historical = new CurrencyRates.Rates("0.2", "0.6", "105.5215", "74.035");
        Mockito.when(dao.getLatestRates(client, apiKey, base)).thenReturn(latest);
        Mockito.when(dao.getHistoricalRates(client, date, apiKey, base)).thenReturn(historical);
        Mockito.when(dao.getRate(latest, "RUB")).thenReturn("74.035");
        Mockito.when(dao.getRate(historical, "RUB")).thenReturn("74.035");
        Mockito.when(dao.getRate(latest, "EUR")).thenReturn("0.4");
        Mockito.when(dao.getRate(historical, "EUR")).thenReturn("0.2");
    }


    @Test
    void testGetExchangeRate() {
        Assertions.assertEquals(0, service.getExchangeRate("RUB"));
        Assertions.assertEquals(0.2, service.getExchangeRate("EUR"));
    }
}