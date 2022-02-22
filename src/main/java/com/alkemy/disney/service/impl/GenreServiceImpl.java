package com.alkemy.disney.service.impl;

import com.alkemy.disney.dto.GenreDTO;
import com.alkemy.disney.entity.GenreEntity;
import com.alkemy.disney.mapper.GenreMapper;
import com.alkemy.disney.repository.GenreRepository;
import com.alkemy.disney.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreMapper genreMapper;
    @Autowired
    private GenreRepository genreRepository;

    public GenreDTO save(GenreDTO dto) {
        GenreEntity entity = genreMapper.genreDTO2Entity(dto);
        GenreEntity genreSaved = genreRepository.save(entity);
        GenreDTO result = genreMapper.genreEntity2DTO(genreSaved);
        return result;
    }
}
