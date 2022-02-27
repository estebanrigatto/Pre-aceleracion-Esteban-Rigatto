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
import java.util.Optional;

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
        Optional<FilmEntity> entity = filmRepository.findById(id);
        FilmDTO result = filmMapper.filmEntity2DTO(entity.get(), true);
        return result;
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
            return null;
            //    throw new NotFoundException("Requested film was not found.");
        }
    }

    public void delete(String id) {
        //if (characterRepository.findById(id) == null)
            //throw new NotFoundException("Requested character was not found");
        filmRepository.deleteById(id);
    }

}
