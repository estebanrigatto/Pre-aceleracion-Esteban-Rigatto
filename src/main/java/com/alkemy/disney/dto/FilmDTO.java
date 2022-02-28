package com.alkemy.disney.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FilmDTO {

    private String id;
    private String picture;
    private String title;
    private String release;
    private Integer rating;
    private GenreDTO genre;
    private List<CharacterDTO> characters;

}
