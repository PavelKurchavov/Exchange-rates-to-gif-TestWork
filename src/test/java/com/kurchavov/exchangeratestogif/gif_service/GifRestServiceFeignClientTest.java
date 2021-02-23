package com.kurchavov.exchangeratestogif.gif_service;

import com.kurchavov.exchangeratestogif.gif_service.model.Gif;
import com.kurchavov.exchangeratestogif.gif_service.rest_service_client.GifRestServiceFeignClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestPropertySource;
import java.util.ArrayList;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class GifRestServiceFeignClientTest {

    @MockBean
    GifRestServiceFeignClient mockClient;

    @Autowired
    ApplicationContext ctx;

    @Value("${giphy.apiKey}")
    String apiKey;

    Gif expectedGif;


    @BeforeEach
    void setUp() {
        Gif.GifData expectedGifData = new Gif.GifData("gif", "https://giphy.com/embed/ZadFL1jG6hOH1s9CQ4");
        ArrayList<Gif.GifData> expectedArrayList = new ArrayList<>();
        expectedArrayList.add(expectedGifData);
        expectedGif = new Gif(expectedArrayList, new Gif.Pagination(6887, 2, 234), new Gif.Meta(200, "OK", "2myuil27qbgvl9xae4zjqeamf83oqmvtgfop9t87"));

        Mockito.when(mockClient.searchGif(apiKey, "rich", 234)).thenReturn(expectedGif);
    }


    @Test
    void searchGif() {
        GifRestServiceFeignClient client = ctx.getBean(GifRestServiceFeignClient.class);
        Assertions.assertEquals(expectedGif, client.searchGif(apiKey, "rich", 234));
        Mockito.verify(mockClient).searchGif(apiKey, "rich", 234);
    }
}