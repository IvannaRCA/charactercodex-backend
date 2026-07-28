package com.ivanna.charactercodex.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ivanna.charactercodex.dto.request.CharacterCreateDto;
import com.ivanna.charactercodex.dto.response.CharacterDetailResponseDto;
import com.ivanna.charactercodex.dto.response.CharacterListResponseDto;
import com.ivanna.charactercodex.entity.CharClass;
import com.ivanna.charactercodex.entity.Inventory;
import com.ivanna.charactercodex.entity.PlayerCharacter;
import com.ivanna.charactercodex.entity.Race;
import com.ivanna.charactercodex.entity.User;
import com.ivanna.charactercodex.exception.AccessDeniedException;
import com.ivanna.charactercodex.exception.EntityNotFoundException;
import com.ivanna.charactercodex.mapper.CharacterMapper;
import com.ivanna.charactercodex.repository.CharClassRepository;
import com.ivanna.charactercodex.repository.InventoryRepository;
import com.ivanna.charactercodex.repository.PlayerCharacterRepository;
import com.ivanna.charactercodex.repository.RaceRepository;
import com.ivanna.charactercodex.repository.UserRepository;

@Service
public class CharacterServiceImp implements CharacterService {
    
    private final PlayerCharacterRepository characterRepository;
    private final UserRepository userRepository;
    private final RaceRepository raceRepository;
    private final CharClassRepository charClassRepository;
    private final InventoryRepository inventoryRepository;
    private final CharacterMapper characterMapper;
    
    public CharacterServiceImp(PlayerCharacterRepository characterRepository, UserRepository userRepository, RaceRepository raceRepository, CharClassRepository charClassRepository, InventoryRepository inventoryRepository, CharacterMapper characterMapper) {
        this.characterRepository = characterRepository;
        this.userRepository = userRepository;
        this.raceRepository = raceRepository;
        this.charClassRepository = charClassRepository;
        this.inventoryRepository = inventoryRepository;
        this.characterMapper = characterMapper;
    }

    @Override
    public List<CharacterListResponseDto> getAllCharacters(String userEmail) {
        User user = getUserByEmail(userEmail);
        return characterMapper.toListOfCharactersDto(characterRepository.findAllByUserId(user.getId()));
    }

    @Override
    public CharacterDetailResponseDto getCharacterById(UUID characterId, String userEmail) {
        return characterMapper.toCharacterDetailDto(getUserCharacter(characterId, userEmail));
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
        PlayerCharacter character = getUserCharacter(characterId, userEmail);
        Race race = getRaceById(dto.raceId());
        CharClass charClass = getClassById(dto.classId());
    
        characterMapper.updateCharacterEntity(character, dto, race, charClass);
    
        return characterMapper.toCharacterDetailDto(characterRepository.save(character));
    
    }

    @Override
    public void deleteCharacter(UUID characterId, String userEmail) {
        characterRepository.delete(getUserCharacter(characterId, userEmail));
    }

    private User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
        .orElseThrow(() -> new EntityNotFoundException(User.class, email));
    }

    private PlayerCharacter getUserCharacter(UUID characterId, String userEmail) {
        PlayerCharacter character = characterRepository.findById(characterId)
        .orElseThrow(() -> new EntityNotFoundException(PlayerCharacter.class, characterId.toString()));

        User user = getUserByEmail(userEmail);
        if (!character.getUser().getId().equals(user.getId())) {
            throw new AccessDeniedException(PlayerCharacter.class);
        }

        return character;
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
}
