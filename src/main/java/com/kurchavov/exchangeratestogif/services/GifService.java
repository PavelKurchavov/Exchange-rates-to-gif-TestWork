package com.kurchavov.exchangeratestogif.services;

import com.kurchavov.exchangeratestogif.dao.GifDao;
import com.kurchavov.exchangeratestogif.model.Gif;
import com.kurchavov.exchangeratestogif.rest_service_client.GifRestServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

// Сервис, предоставляющий гифку

@Service
@RequiredArgsConstructor
public class GifService {

    private final GifRestServiceClient client;
    private final GifDao dao;

    @Value("${giphy.apiKey}")
    String apiKey;

    // Метод возвращает ссылку на рандомную гифку по поисковому запросу (от первой до 1001)

    public String getRandomGif(String searchQuery) {
        Gif.GifData gifData = dao.getGifData(client, apiKey, searchQuery, (int) (Math.random() * 1000 + 1));
        return gifData.getEmbed_url();
    }
}
