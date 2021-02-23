package com.kurchavov.exchangeratestogif.gif_service.rest_service_client;


import com.kurchavov.exchangeratestogif.gif_service.model.Gif;

public interface GifRestServiceClient {
    Gif searchGif(String apiKey, String searchQuery, int offset);
}
