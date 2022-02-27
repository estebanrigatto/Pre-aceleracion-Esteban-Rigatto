package com.alkemy.disney.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CharacterFiltersDTO {

    private String name;
    private Integer age;
    private Integer weight;
    private List<String> films;

    public CharacterFiltersDTO(String name, Integer age, Integer weight, List<String> films) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.films = films;
    }
}
