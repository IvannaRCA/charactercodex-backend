package com.ivanna.charactercodex.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ivanna.charactercodex.constant.SpellcasterClasses;
import com.ivanna.charactercodex.dto.request.CharacterCreateDto;
import com.ivanna.charactercodex.dto.response.CharacterDetailResponseDto;
import com.ivanna.charactercodex.dto.response.CharacterListResponseDto;
import com.ivanna.charactercodex.entity.CharClass;
import com.ivanna.charactercodex.entity.Inventory;
import com.ivanna.charactercodex.entity.PlayerCharacter;
import com.ivanna.charactercodex.entity.Race;
import com.ivanna.charactercodex.entity.Spell;
import com.ivanna.charactercodex.entity.User;
import com.ivanna.charactercodex.exception.EntityNotFoundException;
import com.ivanna.charactercodex.exception.InvalidOperationException;
import com.ivanna.charactercodex.mapper.CharacterMapper;
import com.ivanna.charactercodex.repository.CharClassRepository;
import com.ivanna.charactercodex.repository.InventoryRepository;
import com.ivanna.charactercodex.repository.PlayerCharacterRepository;
import com.ivanna.charactercodex.repository.RaceRepository;
import com.ivanna.charactercodex.repository.SpellRepository;
import com.ivanna.charactercodex.repository.UserRepository;
import com.ivanna.charactercodex.security.CharacterAccessGuard;

@Service
public class CharacterServiceImp implements CharacterService {
    
    private final PlayerCharacterRepository characterRepository;
    private final UserRepository userRepository;
    private final RaceRepository raceRepository;
    private final CharClassRepository charClassRepository;
    private final InventoryRepository inventoryRepository;
    private final SpellRepository spellRepository;
    private final CharacterAccessGuard characterAccessGuard;
    private final CharacterMapper characterMapper;
    
    public CharacterServiceImp(PlayerCharacterRepository characterRepository, UserRepository userRepository, RaceRepository raceRepository, CharClassRepository charClassRepository, InventoryRepository inventoryRepository, SpellRepository spellRepository, CharacterAccessGuard characterAccessGuard, CharacterMapper characterMapper) {
        this.characterRepository = characterRepository;
        this.userRepository = userRepository;
        this.raceRepository = raceRepository;
        this.charClassRepository = charClassRepository;
        this.inventoryRepository = inventoryRepository;
        this.spellRepository = spellRepository;
        this.characterAccessGuard = characterAccessGuard;
        this.characterMapper = characterMapper;
    }

    @Override
    public List<CharacterListResponseDto> getAllCharacters(String userEmail) {
        User user = getUserByEmail(userEmail);
        return characterMapper.toListOfCharactersDto(characterRepository.findAllByUserId(user.getId()));
    }

    @Override
    public CharacterDetailResponseDto getCharacterById(UUID characterId, String userEmail) {
        return characterMapper.toCharacterDetailDto(characterAccessGuard.getUserCharacter(characterId, userEmail));
    }

    @Override
    public CharacterDetailResponseDto createCharacter(CharacterCreateDto dto, String userEmail) {
        User user = getUserByEmail(userEmail);
        Race race = getRaceById(dto.raceId());
        CharClass charClass = getClassById(dto.classId());

        PlayerCharacter character = characterMapper.toCharacterEntity(dto, user, race, charClass);
        PlayerCharacter characterSaved = characterRepository.save(character);
        
        createInventory(characterSaved);

        return characterMapper.toCharacterDetailDto(characterSaved);
    }
    
    @Override
    public CharacterDetailResponseDto updateCharacter(UUID characterId, CharacterCreateDto dto, String userEmail) {
        PlayerCharacter character = characterAccessGuard.getUserCharacter(characterId, userEmail);
        Race race = getRaceById(dto.raceId());
        CharClass charClass = getClassById(dto.classId());
    
        characterMapper.updateCharacterEntity(character, dto, race, charClass);
    
        return characterMapper.toCharacterDetailDto(characterRepository.save(character));
    }

    @Override
    public void deleteCharacter(UUID characterId, String userEmail) {
        characterRepository.delete(characterAccessGuard.getUserCharacter(characterId, userEmail));
    }

    private User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
        .orElseThrow(() -> new EntityNotFoundException(User.class, email));
    }

    private Race getRaceById(UUID raceId) {
        return raceRepository.findById(raceId)
        .orElseThrow(() -> new EntityNotFoundException(Race.class, raceId.toString()));
    }

    private CharClass getClassById(UUID classId) {
        return charClassRepository.findById(classId)
        .orElseThrow(() -> new EntityNotFoundException(CharClass.class, classId.toString()));
    }

    private void createInventory(PlayerCharacter character) {
        Inventory inventory = Inventory.builder()
                .character(character)
                .gold(0)
                .build();
        inventoryRepository.save(inventory);
        character.setInventory(inventory);
    }

    @Override
    public CharacterDetailResponseDto addSpell(UUID characterId, UUID spellId, String userEmail) {
        PlayerCharacter character = characterAccessGuard.getUserCharacter(characterId, userEmail);
        validateSpellcaster(character);

        Spell spell = spellRepository.findById(spellId)
            .orElseThrow(() -> new EntityNotFoundException(Spell.class, spellId.toString()));

        character.getSpells().add(spell);

        return characterMapper.toCharacterDetailDto(characterRepository.save(character));
    }

    @Override
    public CharacterDetailResponseDto removeSpell(UUID characterId, UUID spellId, String userEmail) {
        PlayerCharacter character = characterAccessGuard.getUserCharacter(characterId, userEmail);
        validateSpellcaster(character);

        Spell spell = spellRepository.findById(spellId)
            .orElseThrow(() -> new EntityNotFoundException(Spell.class, spellId.toString()));

        character.getSpells().remove(spell);

        return characterMapper.toCharacterDetailDto(characterRepository.save(character));
    }

    private void validateSpellcaster(PlayerCharacter character) {
        if (!SpellcasterClasses.NAMES.contains(character.getCharClass().getName().toUpperCase())) {
            throw new InvalidOperationException(SpellcasterClasses.NAMES.stream().toList());
        }
    }
}
