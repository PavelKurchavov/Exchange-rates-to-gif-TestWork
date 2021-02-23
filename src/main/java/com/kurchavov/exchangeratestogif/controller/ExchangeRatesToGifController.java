package com.kurchavov.exchangeratestogif.controller;


import com.kurchavov.exchangeratestogif.exchangeratestogif_service.ExchangeRatesToGifService;
import com.kurchavov.exchangeratestogif.exchangeratestogif_service.ExchangeRatesToGifServiceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
public class ExchangeRatesToGifController {
    private final ExchangeRatesToGifService service;

    @Value("${oxr.base}")
    private String baseCurrency;

    @GetMapping("/get_gif")
    public String getRatesAndReturnGif(@RequestParam(value = "to", defaultValue = "EUR") String to, Model model) {
            ExchangeRatesToGifServiceResponse response;

            try {
              response = service.getResponse(to);
            } catch (NullPointerException e) {
                return "404notFound";
            }

            String searchQuery = response.getSearchQuery();
            model.addAttribute("linkToGif", response.getGifEmbedUrl());
            model.addAttribute("baseCurrency", baseCurrency);
            model.addAttribute("currencyTo", to);

        return searchQuery;
    }
}
