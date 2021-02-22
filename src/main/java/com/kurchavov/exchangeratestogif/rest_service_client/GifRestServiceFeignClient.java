package com.kurchavov.exchangeratestogif.rest_service_client;

import com.kurchavov.exchangeratestogif.model.Gif;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "giphy", url = "https://api.giphy.com")
public interface GifRestServiceFeignClient extends GifRestServiceClient {
    @Override
    @GetMapping("/v1/gifs/search?api_key={apiKey}&q={searchQuery}&limit=1&offset={offset}")
    Gif searchGif(@PathVariable("apiKey") String apiKey, @PathVariable("searchQuery") String searchQuery, @PathVariable("offset") int offset);
}

