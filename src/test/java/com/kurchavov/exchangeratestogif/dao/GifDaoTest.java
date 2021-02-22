package com.kurchavov.exchangeratestogif.dao;

import com.kurchavov.exchangeratestogif.model.Gif;
import com.kurchavov.exchangeratestogif.rest_service_client.GifRestServiceClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import java.util.ArrayList;


@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class GifDaoTest {

    @MockBean
    GifRestServiceClient mockClient;

    @Value("${giphy.apiKey}")
    String apiKey;

    @Autowired
    GifDao dao;

    Gif.GifData expectedGifData;

    @BeforeEach
    void setUp() {
        expectedGifData = new Gif.GifData("gif", "https://giphy.com/embed/ZadFL1jG6hOH1s9CQ4");
        ArrayList<Gif.GifData> expectedArrayList = new ArrayList<>();
        expectedArrayList.add(expectedGifData);
        Gif expectedGif = new Gif(expectedArrayList,new Gif.Pagination(6887, 2, 234), new Gif.Meta(200, "OK", "2myuil27qbgvl9xae4zjqeamf83oqmvtgfop9t87"));

        Mockito.when(mockClient.searchGif(apiKey, "rich", 234)).thenReturn(expectedGif);
    }


    @Test
    void getGifData() {
        Assertions.assertEquals(expectedGifData, dao.getGifData(mockClient, apiKey, "rich", 234));
    }


    @Test
    void getGifEmbedUrl() {
        Assertions.assertEquals(expectedGifData.getEmbed_url(), dao.getGifEmbedUrl(expectedGifData));
    }
}