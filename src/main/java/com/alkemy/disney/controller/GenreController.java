package com.alkemy.disney.controller;

import com.alkemy.disney.dto.GenreDTO;
import com.alkemy.disney.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping("/{id}")
    public ResponseEntity<GenreDTO> getDetailsById(@PathVariable String id) {
        GenreDTO genre = genreService.getDetailsById(id);
        return ResponseEntity.ok(genre);
    }

    @PostMapping
    public ResponseEntity<GenreDTO> save(@RequestBody GenreDTO genre) {
        GenreDTO genreSaved = genreService.save(genre);
        return ResponseEntity.status(HttpStatus.CREATED).body(genreSaved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenreDTO> update(@PathVariable String id, @RequestBody GenreDTO genre) {
        GenreDTO genreUpdated = genreService.update(id, genre);
        return ResponseEntity.ok().body(genreUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        genreService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
