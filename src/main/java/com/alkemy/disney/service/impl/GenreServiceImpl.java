package com.alkemy.disney.service.impl;

import com.alkemy.disney.dto.GenreDTO;
import com.alkemy.disney.entity.GenreEntity;
import com.alkemy.disney.mapper.GenreMapper;
import com.alkemy.disney.repository.GenreRepository;
import com.alkemy.disney.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreMapper genreMapper;
    @Autowired
    private GenreRepository genreRepository;

    public GenreDTO getDetailsById(String id) {
        Optional<GenreEntity> entity = genreRepository.findById(id);
        GenreDTO result = genreMapper.genreEntity2DTO(entity.get());
        return result;
    }

    public GenreDTO save(GenreDTO dto) {
        GenreEntity entity = genreMapper.genreDTO2Entity(dto);
        GenreEntity entitySaved = genreRepository.save(entity);
        GenreDTO result = genreMapper.genreEntity2DTO(entitySaved);
        return result;
    }

    public GenreDTO update(String id, GenreDTO dto) {
        Optional<GenreEntity> result = genreRepository.findById(id);
        if (result.isPresent()) {
            GenreEntity entity = genreMapper.updateGenreDTO2Entity(result.get(), dto);
            GenreEntity entityUpdated = genreRepository.save(entity);
            GenreDTO dtoUpdated = genreMapper.genreEntity2DTO(entityUpdated);
            return dtoUpdated;
        } else {
            return null;
        //    throw new NotFoundException("Requested genre was not found.");
        }
    }

    public void delete(String id) {
        //if (genreRepository.findById(id) == null)
            //throw new NotFoundException("Requested genre was not found");
        genreRepository.deleteById(id);
    }
}
