package com.alkemy.disney.service;

import com.alkemy.disney.dto.FilmBasicDTO;
import com.alkemy.disney.dto.FilmDTO;

import java.util.List;

public interface FilmService {

    List<FilmBasicDTO> getBasicDTOList();

    List<FilmDTO> getAllFilms();

    FilmDTO getDetailsById(String id);

    List<FilmBasicDTO> getByFilters(String title, String genreId, String order);

    FilmDTO save(FilmDTO dto);

    FilmDTO update(String id, FilmDTO dto);

    void delete(String id);

    FilmDTO addCharacter(String filmId, String characterId);

    FilmDTO removeCharacter(String filmId, String characterId);

}
