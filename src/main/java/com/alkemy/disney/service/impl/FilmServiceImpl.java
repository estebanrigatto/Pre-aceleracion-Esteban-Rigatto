package com.alkemy.disney.service.impl;

import com.alkemy.disney.dto.FilmBasicDTO;
import com.alkemy.disney.dto.FilmDTO;
import com.alkemy.disney.dto.FilmFiltersDTO;
import com.alkemy.disney.entity.FilmEntity;
import com.alkemy.disney.mapper.FilmMapper;
import com.alkemy.disney.repository.FilmRepository;
import com.alkemy.disney.repository.specifications.FilmSpecification;
import com.alkemy.disney.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmServiceImpl implements FilmService {

    @Autowired
    private FilmMapper filmMapper;
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private FilmSpecification filmSpecification;

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
        FilmEntity entity = filmRepository.getById(id);
        FilmDTO result = filmMapper.filmEntity2DTO(entity, true);
        return result;
    }

    public List<FilmDTO> getByFilters(String title, String genreId, String order) {
        FilmFiltersDTO filtersDTO = new FilmFiltersDTO(title, genreId, order);
        List<FilmEntity> entities = filmRepository.findAll(filmSpecification.getByFilters(filtersDTO));
        List<FilmDTO> dtos = filmMapper.filmEntityList2DTOList(entities, true);
        return dtos;
    }

    public FilmDTO save(FilmDTO dto){
        FilmEntity entity = filmMapper.filmDTO2Entity(dto);
        FilmEntity entitySaved = filmRepository.save(entity);
        FilmDTO result = filmMapper.filmEntity2DTO(entitySaved, true);
        return result;
    }

    public FilmDTO update(String id, FilmDTO dto) {
        FilmEntity film = filmRepository.getById(id);
        FilmEntity entity = filmMapper.updateFilmDTO2Entity(film, dto);
        FilmEntity entityUpdated = filmRepository.save(entity);
        FilmDTO result = filmMapper.filmEntity2DTO(entityUpdated, false);
        return result;
    }

    public void delete(String id) {
        filmRepository.deleteById(id);
    }

}
