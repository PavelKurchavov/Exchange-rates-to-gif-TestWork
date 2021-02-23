package com.kurchavov.exchangeratestogif.exchangeratestogif_service;

import com.kurchavov.exchangeratestogif.currency_rates_service.CurrencyRatesService;
import com.kurchavov.exchangeratestogif.gif_service.GifService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ExchangeRatesToGifService {
    private final CurrencyRatesService currencyRatesService;
    private final GifService gifService;

    public ExchangeRatesToGifServiceResponse getResponse(String currencyCode) {
        String searchQuery;
        String gifEmbedUrl;
        double exchangeRate = currencyRatesService.getExchangeRate(currencyCode);

        if(exchangeRate > 0) {
            searchQuery = "rich";
        } else {
            searchQuery = "broke";
        }
        gifEmbedUrl = gifService.getRandomGif(searchQuery);

        return new ExchangeRatesToGifServiceResponse(searchQuery, gifEmbedUrl);
    }
}
