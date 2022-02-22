package com.alkemy.disney.service;

import com.alkemy.disney.dto.FilmBasicDTO;
import com.alkemy.disney.dto.FilmDTO;

import java.util.List;

public interface FilmService {

    List<FilmBasicDTO> getBasicDTOList();

    List<FilmDTO> getAllFilms();

    FilmDTO save(FilmDTO dto);

    FilmDTO getDetailsById(String id);

    FilmDTO update(String id, FilmDTO dto);

    void delete(String id);

}