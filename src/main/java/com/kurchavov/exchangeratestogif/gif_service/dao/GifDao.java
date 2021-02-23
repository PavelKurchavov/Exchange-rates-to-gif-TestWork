package com.kurchavov.exchangeratestogif.gif_service.dao;

import com.kurchavov.exchangeratestogif.gif_service.model.Gif;
import com.kurchavov.exchangeratestogif.gif_service.rest_service_client.GifRestServiceClient;
import lombok.NonNull;
import org.springframework.stereotype.Component;

// Класс доступа к данным о гифке

@Component
public class GifDao {

    // Возвращает объект с данными о гифке

    public Gif.GifData getGifData(@NonNull GifRestServiceClient client, String apiKey, String searchQuery, int offset) {
        Gif gif = client.searchGif(apiKey, searchQuery, offset);
        return gif.getData().get(0);
    }

    // Возвращает ссылку на гифку

    public String getGifEmbedUrl(Gif.GifData gifData) {
        return gifData.getEmbedUrl();
    }
}
