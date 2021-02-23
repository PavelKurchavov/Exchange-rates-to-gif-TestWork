package com.kurchavov.exchangeratestogif.gif_service.dao;

import com.kurchavov.exchangeratestogif.currency_rates_service.dao.CurrencyRatesDao;
import com.kurchavov.exchangeratestogif.currency_rates_service.model.CurrencyRates;
import com.kurchavov.exchangeratestogif.currency_rates_service.rest_service_client.CurrencyRateRestServiceFeignClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;


@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class CurrencyRatesDaoTest {

    @MockBean
    CurrencyRateRestServiceFeignClient feignClient;

    @Value("${oxr.apiKey}")
    String apiKey;

    @Value("${oxr.base}")
    String base;

    CurrencyRatesDao dao;
    CurrencyRates rates;
    String date;


    @BeforeEach
    void setUp() {
        rates = new CurrencyRates("Usage subject to terms: https://openexchangerates.org/terms",
                "https://openexchangerates.org/license",
                "USD", new CurrencyRates.Rates("0.825202", "0.713598", "105.5215", "74.035"));

        dao = new CurrencyRatesDao();
        date = "2021-02-20";

        Mockito.when(feignClient.getHistoricalRates(date, apiKey, base)).thenReturn(rates);
    }


    @Test
    void getHistoricalRates() {
        Assertions.assertEquals(rates.getRates(), dao.getHistoricalRates(feignClient, date, apiKey, base));
    }


    @Test
    void getRate() {
        Assertions.assertEquals(rates.getRates().getGBP(), dao.getRate(rates.getRates(), "GBP"));
    }
}