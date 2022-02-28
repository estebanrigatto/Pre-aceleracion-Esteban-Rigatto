package com.alkemy.disney.service;

import com.alkemy.disney.dto.CharacterBasicDTO;
import com.alkemy.disney.dto.CharacterDTO;

import java.util.List;

public interface CharacterService {

    List<CharacterBasicDTO> getBasicDTOList();

    List<CharacterDTO> getAllCharacters();

    CharacterDTO getDetailsById(String id);

    List<CharacterBasicDTO> getByFilters(String name, Integer age, Integer weight, List<String> films);

    CharacterDTO save(CharacterDTO dto);

    CharacterDTO update(String id, CharacterDTO dto);

    void delete(String id);

}
