package com.ivanna.charactercodex.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ivanna.charactercodex.dto.request.CharacterCreateDto;
import com.ivanna.charactercodex.dto.response.CharacterDetailResponseDto;
import com.ivanna.charactercodex.dto.response.CharacterListResponseDto;
import com.ivanna.charactercodex.service.CharacterService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/v1/characters")
public class CharacterController {
    private final CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping
    public ResponseEntity<List<CharacterListResponseDto>> getAllCharacters(Authentication authentication) {
        return new ResponseEntity<>(characterService.getAllCharacters(authentication.getName()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CharacterDetailResponseDto> getCharacterById(
        @PathVariable UUID id, 
        Authentication authentication) {
        
        return new ResponseEntity<>(characterService.getCharacterById(id, authentication.getName()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CharacterDetailResponseDto> createCharacter(
        @Valid @RequestBody CharacterCreateDto dto,
        Authentication authentication) {
        
        CharacterDetailResponseDto created = characterService.createCharacter(dto, authentication.getName());
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CharacterDetailResponseDto> updateCharacter(
        @PathVariable UUID id, 
        @Valid @RequestBody CharacterCreateDto dto, 
        Authentication authentication) {
    
        return new ResponseEntity<>(characterService.updateCharacter(id, dto, authentication.getName()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCharacter(
        @PathVariable UUID id, 
        Authentication authentication) {
        
        characterService.deleteCharacter(id, authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
