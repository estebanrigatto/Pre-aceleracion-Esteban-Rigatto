package com.alkemy.disney.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilmFiltersDTO {

    private String title;
    private String genreId;
    private String order;

    public FilmFiltersDTO(String title, String genreId, String order) {
        this.title = title;
        this.genreId = genreId;
        this.order = order;
    }
}
