package com.alkemy.disney.mapper;

import com.alkemy.disney.dto.CharacterBasicDTO;
import com.alkemy.disney.dto.CharacterDTO;
import com.alkemy.disney.dto.FilmDTO;
import com.alkemy.disney.entity.CharacterEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CharacterMapper {

    private FilmMapper filmMapper;

    public List<CharacterBasicDTO> characterEntityList2BasicDTOList(List<CharacterEntity> entities) {
        List<CharacterBasicDTO> dtos = new ArrayList<>();
        CharacterBasicDTO basicDTO;
        for (CharacterEntity entity : entities) {
            basicDTO = new CharacterBasicDTO();
            basicDTO.setId(entity.getId());
            basicDTO.setName(entity.getName());
            basicDTO.setPicture(entity.getPicture());
            dtos.add(basicDTO);
        }
        return dtos;
    }

    public CharacterEntity characterDTO2Entity(CharacterDTO dto) {
        CharacterEntity entity = new CharacterEntity();
        entity.setPicture(dto.getPicture());
        entity.setName(dto.getName());
        entity.setAge(dto.getAge());
        entity.setWeight(dto.getWeight());
        entity.setStory(dto.getStory());
        return entity;
    }

    public CharacterDTO characterEntity2DTO(CharacterEntity entity, boolean loadFilms) {
        CharacterDTO dto = new CharacterDTO();
        dto.setId(entity.getId());
        dto.setPicture(entity.getPicture());
        dto.setName(entity.getName());
        dto.setAge(entity.getAge());
        dto.setWeight(entity.getWeight());
        dto.setStory(entity.getStory());
        if (loadFilms) {
            List<FilmDTO> dtos = filmMapper.filmEntityList2DTOList(entity.getFilms(), false);
            dto.setFilms(dtos);
        }
        return dto;
    }

    public List<CharacterDTO> characterEntityList2DTOList(List<CharacterEntity> entities, boolean loadFilms) {
        List<CharacterDTO> dtos = new ArrayList<>();
        for (CharacterEntity entity : entities) {
            dtos.add(characterEntity2DTO(entity, loadFilms));
        }
        return dtos;
    }

    public List<CharacterEntity> characterDTOList2EntityList(List<CharacterDTO> dtos) {
        List<CharacterEntity> entities = new ArrayList<>();
        for (CharacterDTO dto : dtos) {
            entities.add(characterDTO2Entity(dto));
        }
        return entities;
    }

    public CharacterEntity updateCharacterDTO2Entity(CharacterEntity entity, CharacterDTO dto) {
        entity.setName(dto.getName());
        entity.setPicture(dto.getPicture());
        entity.setAge(dto.getAge());
        entity.setWeight(dto.getWeight());
        entity.setStory(dto.getStory());
        return entity;
    }

}
