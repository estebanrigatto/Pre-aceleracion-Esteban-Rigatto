package com.alkemy.disney.controller;

import com.alkemy.disney.dto.CharacterDTO;
import com.alkemy.disney.dto.FilmBasicDTO;
import com.alkemy.disney.dto.FilmDTO;
import com.alkemy.disney.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movies")
public class FilmController {

    @Autowired
    private FilmService filmService;

    @GetMapping
    public ResponseEntity<List<FilmBasicDTO>> getBasicDTOList() {
        List<FilmBasicDTO> filmsBasic = filmService.getBasicDTOList();
        return ResponseEntity.ok().body(filmsBasic);
    }

    @GetMapping("/all")
    public ResponseEntity<List<FilmDTO>> getAll() {
        List<FilmDTO> films = filmService.getAllFilms();
        return ResponseEntity.ok().body(films);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmDTO> getDetailsById(@PathVariable String id) {
        FilmDTO film = filmService.getDetailsById(id);
        return ResponseEntity.ok(film);
    }

    @GetMapping
    public ResponseEntity<List<FilmDTO>> getDetailsByFilters(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String genreId,
            @RequestParam(required = false, defaultValue = "ASC") String order
    ) {
        List<FilmDTO> films = filmService.getByFilters(title, genreId, order);
        return ResponseEntity.ok(films);
    }

    @PostMapping
    public ResponseEntity<FilmDTO> save(@RequestBody FilmDTO film) {
        FilmDTO filmSaved = filmService.save(film);
        return ResponseEntity.status(HttpStatus.CREATED).body(filmSaved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FilmDTO> update(@PathVariable String id, @RequestBody FilmDTO film) {
        FilmDTO filmUpdated = filmService.update(id, film);
        return ResponseEntity.ok().body(filmUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        filmService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
