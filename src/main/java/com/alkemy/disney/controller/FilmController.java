package com.alkemy.disney.controller;

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

    @GetMapping("/{id}")
    public ResponseEntity<FilmDTO> getDetailsById(@PathVariable String id) {
        FilmDTO film = filmService.getDetailsById(id);
        return ResponseEntity.ok(film);
    }

    @GetMapping
    public ResponseEntity<List<FilmBasicDTO>> getDetailsByFilters(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String genreId,
            @RequestParam(required = false, defaultValue = "ASC") String order
    ) {
        List<FilmBasicDTO> films = filmService.getByFilters(title, genreId, order);
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

    @PostMapping("/{filmId}/characters/{characterId}")
    public ResponseEntity<FilmDTO> addCharacter(@PathVariable String filmId, @PathVariable String characterId) {
        FilmDTO filmUpdated = filmService.addCharacter(filmId, characterId);
        return ResponseEntity.ok().body(filmUpdated);
    }

    @DeleteMapping("/{filmId}/characters/{characterId}")
    public ResponseEntity<FilmDTO> removeCharacter(@PathVariable String filmId, @PathVariable String characterId) {
        FilmDTO filmUpdated = filmService.removeCharacter(filmId, characterId);
        return ResponseEntity.ok().body(filmUpdated);
    }

}
