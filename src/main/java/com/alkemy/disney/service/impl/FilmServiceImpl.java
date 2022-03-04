package com.alkemy.disney.service.impl;

import com.alkemy.disney.dto.FilmBasicDTO;
import com.alkemy.disney.dto.FilmDTO;
import com.alkemy.disney.dto.FilmFiltersDTO;
import com.alkemy.disney.entity.CharacterEntity;
import com.alkemy.disney.entity.FilmEntity;
import com.alkemy.disney.exception.EntityNotFoundException;
import com.alkemy.disney.exception.ExceptionEnum;
import com.alkemy.disney.mapper.FilmMapper;
import com.alkemy.disney.repository.CharacterRepository;
import com.alkemy.disney.repository.FilmRepository;
import com.alkemy.disney.repository.specifications.FilmSpecification;
import com.alkemy.disney.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilmServiceImpl implements FilmService {

    @Autowired
    private FilmMapper filmMapper;
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private FilmSpecification filmSpecification;
    @Autowired
    @Lazy
    private CharacterRepository characterRepository;

    public List<FilmBasicDTO> getBasicDTOList() {
        List<FilmEntity> entities = filmRepository.findAll();
        List<FilmBasicDTO> result = filmMapper.filmEntityList2BasicDTOList(entities);
        return result;
    }

    public List<FilmDTO> getAllFilms() {
        List<FilmEntity> entities = filmRepository.findAll();
        List<FilmDTO> result = filmMapper.filmEntityList2DTOList(entities, true);
        return result;
    }

    public FilmDTO getDetailsById(String id) {
        Optional<FilmEntity> entity = filmRepository.findById(id);
        if (entity.isPresent()) {
            FilmDTO result = filmMapper.filmEntity2DTO(entity.get(), true);
            return result;
        } else {
            throw new EntityNotFoundException(ExceptionEnum.FILMNOTFOUND.getMessage());
        }
    }

    public List<FilmBasicDTO> getByFilters(String title, String genreId, String order) {
        FilmFiltersDTO filtersDTO = new FilmFiltersDTO(title, genreId, order);
        List<FilmEntity> entities = filmRepository.findAll(filmSpecification.getByFilters(filtersDTO));
        List<FilmBasicDTO> dtos = filmMapper.filmEntityList2BasicDTOList(entities);
        return dtos;
    }

    public FilmDTO save(FilmDTO dto){
        FilmEntity entity = filmMapper.filmDTO2Entity(dto);
        FilmEntity entitySaved = filmRepository.save(entity);
        FilmDTO result = filmMapper.filmEntity2DTO(entitySaved, true);
        return result;
    }

    public FilmDTO update(String id, FilmDTO dto) {
        Optional<FilmEntity> result = filmRepository.findById(id);
        if (result.isPresent()) {
        FilmEntity entity = filmMapper.updateFilmDTO2Entity(result.get(), dto);
        FilmEntity entityUpdated = filmRepository.save(entity);
        FilmDTO dtoUpdated = filmMapper.filmEntity2DTO(entityUpdated, true);
        return dtoUpdated;
        } else {
            throw new EntityNotFoundException(ExceptionEnum.FILMNOTFOUND.getMessage());
        }
    }

    public void delete(String id) {
        if (filmRepository.findById(id) == null) {
            throw new EntityNotFoundException(ExceptionEnum.FILMNOTFOUND.getMessage());
        }
        filmRepository.deleteById(id);
    }

    public FilmDTO addCharacter(String filmId, String characterId) {
        if (filmRepository.findById(filmId) == null) {
            throw new EntityNotFoundException(ExceptionEnum.FILMNOTFOUND.getMessage());
        }
        if (characterRepository.findById(characterId) == null) {
            throw new EntityNotFoundException(ExceptionEnum.CHARACTERNOTFOUND.getMessage());
        }
        FilmEntity film = filmRepository.findById(filmId).get();
        CharacterEntity character = characterRepository.findById(characterId).get();
        film.getCharacters().add(character);
        FilmEntity filmSaved = filmRepository.save(film);
        FilmDTO result = filmMapper.filmEntity2DTO(filmSaved, true);
        return result;
    }

    public FilmDTO removeCharacter(String filmId, String characterId) {
        if (filmRepository.findById(filmId) == null) {
            throw new EntityNotFoundException(ExceptionEnum.FILMNOTFOUND.getMessage());
        }
        if (characterRepository.findById(characterId) == null) {
            throw new EntityNotFoundException(ExceptionEnum.CHARACTERNOTFOUND.getMessage());
        }
        FilmEntity film = filmRepository.findById(filmId).get();
        CharacterEntity character = characterRepository.findById(characterId).get();
        film.getCharacters().remove(character);
        FilmEntity filmSaved = filmRepository.save(film);
        FilmDTO result = filmMapper.filmEntity2DTO(filmSaved, true);
        return result;
    }

}
