package com.kurchavov.exchangeratestogif.currency_rates_service;

import com.kurchavov.exchangeratestogif.currency_rates_service.model.CurrencyRates;
import com.kurchavov.exchangeratestogif.currency_rates_service.rest_service_client.CurrencyRateRestServiceFeignClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestPropertySource;


@SpringBootTest()
@TestPropertySource("classpath:application-test.properties")
class CurrencyRateRestServiceFeignClientTest {

    @MockBean
    CurrencyRateRestServiceFeignClient client;

    @Autowired
    ApplicationContext ctx;

    @Value("${oxr.apiKey}")
    String apiKey;

    @Value("${oxr.base}")
    String base;

    String currencyTo;
    String date;
    CurrencyRates currencyRatesExpected;


    @BeforeEach
    void setUp() {
        currencyTo = "RUB";
        date = "2021-02-20";
        currencyRatesExpected = new CurrencyRates("Usage subject to terms: https://openexchangerates.org/terms",
                "https://openexchangerates.org/license",
                "USD", new CurrencyRates.Rates("0.825202", "0.713598", "105.5215", "74.035"));
    }

    @Test
    void testGetHistoricalRates() {
        Mockito.when(client.getHistoricalRates(date,apiKey, base)).thenReturn(currencyRatesExpected);

        CurrencyRateRestServiceFeignClient feignClient = ctx.getBean(CurrencyRateRestServiceFeignClient.class);
        Assertions.assertEquals(currencyRatesExpected, feignClient.getHistoricalRates(date, apiKey, base));
        Mockito.verify(client).getHistoricalRates(date, apiKey, base);
    }
}