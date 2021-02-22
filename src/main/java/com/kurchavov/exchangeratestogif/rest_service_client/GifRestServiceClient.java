package com.kurchavov.exchangeratestogif.rest_service_client;


import com.kurchavov.exchangeratestogif.model.Gif;

public interface GifRestServiceClient {
    Gif searchGif(String apiKey, String searchQuery, int offset);
}
