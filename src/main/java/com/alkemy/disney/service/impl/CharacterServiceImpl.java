package com.alkemy.disney.service.impl;

import com.alkemy.disney.dto.CharacterBasicDTO;
import com.alkemy.disney.dto.CharacterDTO;
import com.alkemy.disney.dto.CharacterFiltersDTO;
import com.alkemy.disney.entity.CharacterEntity;
import com.alkemy.disney.mapper.CharacterMapper;
import com.alkemy.disney.repository.CharacterRepository;
import com.alkemy.disney.repository.specifications.CharacterSpecification;
import com.alkemy.disney.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    private CharacterMapper characterMapper;
    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private CharacterSpecification characterSpecification;

    public List<CharacterBasicDTO> getBasicDTOList() {
        List<CharacterEntity> entities = characterRepository.findAll();
        List<CharacterBasicDTO> result = characterMapper.characterEntityList2BasicDTOList(entities);
        return result;
    }

    public List<CharacterDTO> getAllCharacters() {
        List<CharacterEntity> entities = characterRepository.findAll();
        List<CharacterDTO> result = characterMapper.characterEntityList2DTOList(entities, true);
        return result;
    }

    public CharacterDTO getDetailsById(String id) {
        CharacterEntity entity = characterRepository.getById(id);
        CharacterDTO result = characterMapper.characterEntity2DTO(entity, true);
        return result;
    }

    public List<CharacterDTO> getByFilters(String name, int age, int weight, List<String> films) {
        CharacterFiltersDTO filtersDTO = new CharacterFiltersDTO(name, age, weight, films);
        List<CharacterEntity> entities = characterRepository.findAll(characterSpecification.getByFilters(filtersDTO));
        List<CharacterDTO> dtos = characterMapper.characterEntityList2DTOList(entities, true);
        return dtos;
    }

    public CharacterDTO save(CharacterDTO dto) {
        CharacterEntity entity = characterMapper.characterDTO2Entity(dto);
        CharacterEntity entitySaved = characterRepository.save(entity);
        CharacterDTO result = characterMapper.characterEntity2DTO(entitySaved, true);
        return result;
    }

    public CharacterDTO update(String id, CharacterDTO dto) {
        CharacterEntity character = characterRepository.getById(id);
        CharacterEntity entity = characterMapper.updateCharacterDTO2Entity(character, dto);
        CharacterEntity entityUpdated = characterRepository.save(entity);
        CharacterDTO result = characterMapper.characterEntity2DTO(entityUpdated, false);
        return result;
    }

    public void delete(String id) {
        characterRepository.deleteById(id);
    }

}
