package com.alkemy.disney.service;

import com.alkemy.disney.dto.GenreDTO;

public interface GenreService {

    GenreDTO getDetailsById(String id);

    GenreDTO save(GenreDTO dto);

    GenreDTO update(String id, GenreDTO dto);

    void delete(String id);

}
