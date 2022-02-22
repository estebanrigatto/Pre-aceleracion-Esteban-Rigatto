package com.alkemy.disney.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CharacterDTO {

    private String id;
    private String picture;
    private String name;
    private int age;
    private int weight;
    private String story;
    private List<FilmDTO> films;

}
