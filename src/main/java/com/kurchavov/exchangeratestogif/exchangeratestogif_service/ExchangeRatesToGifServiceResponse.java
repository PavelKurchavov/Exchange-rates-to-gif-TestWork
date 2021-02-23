package com.kurchavov.exchangeratestogif.exchangeratestogif_service;

import lombok.Getter;

@Getter
public class ExchangeRatesToGifServiceResponse {
    private final String searchQuery;
    private final String gifEmbedUrl;

    public ExchangeRatesToGifServiceResponse(String searchQuery, String gifEmbedUrl) {
        this.searchQuery = searchQuery;
        this.gifEmbedUrl = gifEmbedUrl;
    }
}
