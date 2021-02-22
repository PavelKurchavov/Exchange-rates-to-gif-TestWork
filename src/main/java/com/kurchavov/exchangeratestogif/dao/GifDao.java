package com.kurchavov.exchangeratestogif.dao;

import com.kurchavov.exchangeratestogif.model.Gif;
import com.kurchavov.exchangeratestogif.rest_service_client.GifRestServiceClient;
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
        return gifData.getEmbed_url();
    }
}
