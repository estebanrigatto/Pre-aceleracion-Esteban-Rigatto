package com.alkemy.disney.mapper;

import com.alkemy.disney.dto.GenreDTO;
import com.alkemy.disney.entity.GenreEntity;
import org.springframework.stereotype.Component;

@Component
public class GenreMapper {

    public GenreEntity genreDTO2Entity(GenreDTO dto) {
        GenreEntity entity = new GenreEntity();
        entity.setPicture(dto.getPicture());
        entity.setName(dto.getName());
        return entity;
    }

    public GenreDTO genreEntity2DTO(GenreEntity entity) {
        GenreDTO dto = new GenreDTO();
        dto.setId(entity.getId());
        dto.setPicture(entity.getPicture());
        dto.setName(entity.getName());
        return dto;
    }

}
