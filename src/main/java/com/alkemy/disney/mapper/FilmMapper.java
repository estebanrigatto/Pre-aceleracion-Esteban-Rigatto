package com.alkemy.disney.mapper;

import com.alkemy.disney.dto.FilmBasicDTO;
import com.alkemy.disney.dto.FilmDTO;
import com.alkemy.disney.entity.FilmEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class FilmMapper {

    @Autowired
    private CharacterMapper characterMapper;

    @Autowired
    private GenreMapper genreMapper;

    public List<FilmBasicDTO> filmEntityList2BasicDTOList(List<FilmEntity> entities) {
        List<FilmBasicDTO> dtos = new ArrayList<>();
        FilmBasicDTO basicDTO;
        for (FilmEntity entity : entities) {
            basicDTO = new FilmBasicDTO();
            basicDTO.setId(entity.getId());
            basicDTO.setPicture(entity.getPicture());
            basicDTO.setTitle(entity.getTitle());
            basicDTO.setRelease(entity.getRelease().toString());
            dtos.add(basicDTO);
        }
        return dtos;
    }

    public FilmEntity filmDTO2Entity(FilmDTO dto) {
        FilmEntity entity = new FilmEntity();
        entity.setPicture(dto.getPicture());
        entity.setTitle(dto.getTitle());
        entity.setRelease(stringToLocalDate(dto.getRelease()));
        entity.setRating(dto.getRating());
        return entity;
    }

    public FilmDTO filmEntity2DTO(FilmEntity entity, boolean loadCharacters) {
        FilmDTO dto = new FilmDTO();
        dto.setId(entity.getId());
        dto.setPicture(entity.getPicture());
        dto.setTitle(entity.getTitle());
        dto.setRelease(entity.getRelease().toString());
        dto.setRating(entity.getRating());
        dto.setGenre(genreMapper.genreEntity2DTO(entity.getGenre()));
        if (loadCharacters) {
            dto.setCharacters(characterMapper.characterEntityList2DTOList(entity.getCharacters(), false));
        }
        return dto;
    }

    public List<FilmDTO> filmEntityList2DTOList(List<FilmEntity> entities, boolean loadCharacters) {
        List<FilmDTO> dtos = new ArrayList<>();
        for (FilmEntity entity : entities) {
            dtos.add(filmEntity2DTO(entity, loadCharacters));
        }
        return dtos;
    }

    public FilmEntity updateFilmDTO2Entity(FilmEntity entity, FilmDTO dto) {
        entity.setPicture(dto.getPicture());
        entity.setTitle(dto.getTitle());
        entity.setRelease(stringToLocalDate(dto.getRelease()));
        entity.setRating(dto.getRating());
        return entity;
    }

    public LocalDate stringToLocalDate(String stringDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(stringDate, formatter);
        return date;
    }
}
