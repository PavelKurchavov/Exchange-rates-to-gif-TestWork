package com.kurchavov.exchangeratestogif.currency_rates_service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;

// Объект данных о курсах валют
@Getter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyRates {

        private final String disclaimer;
        private final String license;
        private final String base;
        private final Rates rates;

        @Getter
        @AllArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Rates {
            private final String EUR;
            private final String GBP;
            private final String JPY;
            private final String RUB;
        }
    }

