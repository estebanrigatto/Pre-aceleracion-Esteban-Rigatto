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
import java.util.Optional;

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
        Optional<CharacterEntity> entity = characterRepository.findById(id);
        CharacterDTO result = characterMapper.characterEntity2DTO(entity.get(), false);
        return result;
    }

    public List<CharacterBasicDTO> getByFilters(String name, int age, int weight, List<String> films) {
        CharacterFiltersDTO filtersDTO = new CharacterFiltersDTO(name, age, weight, films);
        List<CharacterEntity> entities = characterRepository.findAll(characterSpecification.getByFilters(filtersDTO));
        List<CharacterBasicDTO> dtos = characterMapper.characterEntityList2BasicDTOList(entities);
        return dtos;
    }

    public CharacterDTO save(CharacterDTO dto) {
        CharacterEntity entity = characterMapper.characterDTO2Entity(dto);
        CharacterEntity entitySaved = characterRepository.save(entity);
        CharacterDTO result = characterMapper.characterEntity2DTO(entitySaved, true);
        return result;
    }

    public CharacterDTO update(String id, CharacterDTO dto) {
        Optional<CharacterEntity> result = characterRepository.findById(id);
        if (result.isPresent()) {
        CharacterEntity entity = characterMapper.updateCharacterDTO2Entity(result.get(), dto);
        CharacterEntity entityUpdated = characterRepository.save(entity);
        CharacterDTO dtoUpdated = characterMapper.characterEntity2DTO(entityUpdated, true);
        return dtoUpdated;
        } else {
            return null;
            //    throw new NotFoundException("Requested character was not found.");
        }
    }

    public void delete(String id) {
        //if (characterRepository.findById(id) == null)
            //throw new NotFoundException("Requested character was not found");
        characterRepository.deleteById(id);
    }

}
