package com.alkemy.disney.controller;

import com.alkemy.disney.dto.CharacterBasicDTO;
import com.alkemy.disney.dto.CharacterDTO;
import com.alkemy.disney.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @GetMapping
    public ResponseEntity<List<CharacterBasicDTO>> getBasicDTOList() {
        List<CharacterBasicDTO> charactersBasic = characterService.getBasicDTOList();
        return ResponseEntity.ok().body(charactersBasic);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CharacterDTO>> getAll() {
        List<CharacterDTO> characters = characterService.getAllCharacters();
        return ResponseEntity.ok().body(characters);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CharacterDTO> getDetailsById(@PathVariable String id) {
        CharacterDTO character = characterService.getDetailsById(id);
        return ResponseEntity.ok(character);
    }

    @GetMapping
    public ResponseEntity<List<CharacterDTO>> getDetailsByFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) int age,
            @RequestParam(required = false) int weight,
            @RequestParam(required = false) List<String> films
    ) {
        List<CharacterDTO> characters = characterService.getByFilters(name, age, weight, films);
        return ResponseEntity.ok(characters);
    }

    @PostMapping
    public ResponseEntity<CharacterDTO> save(@RequestBody CharacterDTO character) {
        CharacterDTO characterSaved = characterService.save(character);
        return ResponseEntity.status(HttpStatus.CREATED).body(characterSaved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CharacterDTO> update(@PathVariable String id, @RequestBody CharacterDTO character) {
        CharacterDTO characterUpdated = characterService.update(id, character);
        return ResponseEntity.ok().body(characterUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        characterService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
