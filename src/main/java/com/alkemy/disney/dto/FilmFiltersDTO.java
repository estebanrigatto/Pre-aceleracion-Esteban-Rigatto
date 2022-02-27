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

    public boolean isASC() { return this.order.compareToIgnoreCase("ASC") == 0;}
    public boolean isDESC() { return this.order.compareToIgnoreCase("DESC") == 0;}

}
