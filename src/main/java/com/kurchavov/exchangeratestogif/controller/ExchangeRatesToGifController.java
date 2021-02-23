package com.kurchavov.exchangeratestogif.controller;

import com.kurchavov.exchangeratestogif.currency_rates_service.CurrencyRatesService;
import com.kurchavov.exchangeratestogif.gif_service.GifService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
public class ExchangeRatesToGifController {
    private final CurrencyRatesService currencyRatesService;
    private final GifService gifService;

    @Value("${oxr.base}")
    private String baseCurrency;

    @GetMapping("/get_gif")
    public String getRatesAndReturnGif(@RequestParam(value = "to", defaultValue = "EUR") String to, Model model) {
        String linkToGif;
        String htmlPage;

        if(currencyRatesService.getExchangeRate(to) > 0) {
            linkToGif = gifService.getRandomGif("rich");
            htmlPage = "rich";
        }
        else {
            linkToGif = gifService.getRandomGif("broke");
            htmlPage = "broke";
        }

        model.addAttribute("linkToGif", linkToGif);
        model.addAttribute("baseCurrency", baseCurrency);
        model.addAttribute("currencyTo", to);

        return htmlPage;
    }
}
