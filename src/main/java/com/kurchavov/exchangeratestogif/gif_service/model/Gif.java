package com.kurchavov.exchangeratestogif.gif_service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;

@Getter
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Gif {
    private final ArrayList<GifData> data;
    private final Pagination PaginationObject;
    private final Meta MetaObject;

    @Getter
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class GifData {
        private final String type;
        private final String embed_url;

        public String getEmbedUrl() {
            return this.embed_url;
        }
    }

    @Getter
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Meta {
        private final float status;
        private final String msg;
        private final String response_id;
    }

    @Getter
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Pagination {
        private final float total_count;
        private final float count;
        private final float offset;
    }
}
