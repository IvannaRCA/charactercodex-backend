package com.ivanna.charactercodex.service;

import java.util.List;
import java.util.UUID;

import com.ivanna.charactercodex.dto.request.CharacterCreateDto;
import com.ivanna.charactercodex.dto.response.CharacterDetailResponseDto;
import com.ivanna.charactercodex.dto.response.CharacterListResponseDto;

public interface CharacterService {
    List<CharacterListResponseDto> getAllCharacters(String userEmail);

    CharacterDetailResponseDto getCharacterById(UUID characterId, String userEmail);

    CharacterDetailResponseDto createCharacter(CharacterCreateDto dto, String userEmail);

    CharacterDetailResponseDto updateCharacter(UUID characterId, CharacterCreateDto dto, String userEmail);

    void deleteCharacter(UUID characterId, String userEmail);
    
    CharacterDetailResponseDto addSpell(UUID characterId, UUID spellId, String userEmail);

    CharacterDetailResponseDto removeSpell(UUID characterId, UUID spellId, String userEmail);
}
