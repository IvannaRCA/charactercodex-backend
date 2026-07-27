package com.ivanna.charactercodex.mapper;

import java.util.Collections;
import java.util.List;

import com.ivanna.charactercodex.dto.request.CharacterCreateDto;
import com.ivanna.charactercodex.dto.response.CharacterDetailResponseDto;
import com.ivanna.charactercodex.dto.response.CharacterListResponseDto;
import com.ivanna.charactercodex.dto.response.InventoryResponseDto;
import com.ivanna.charactercodex.dto.response.ObjectResponseDto;
import com.ivanna.charactercodex.entity.CharClass;
import com.ivanna.charactercodex.entity.Inventory;
import com.ivanna.charactercodex.entity.PlayerCharacter;
import com.ivanna.charactercodex.entity.Race;
import com.ivanna.charactercodex.entity.Spell;
import com.ivanna.charactercodex.entity.User;

public class CharacterMapper {

    public PlayerCharacter toCharacterEntity(CharacterCreateDto dto, User user, Race race, CharClass charClass) {
        return PlayerCharacter.builder()
        .user(user)
        .name(dto.name())
        .level(dto.level())
        .armorClass(dto.armorClass())
        .race(race)
        .charClass(charClass)
        .strength(dto.strength())
        .constitution(dto.constitution())
        .dexterity(dto.dexterity())
        .intelligence(dto.intelligence())
        .wisdom(dto.wisdom())
        .charisma(dto.charisma())
        .description(dto.description())
        .history(dto.history())
        .build();
    }

    public void updateCharacterEntity(PlayerCharacter character, CharacterCreateDto dto, Race race, CharClass charClass) {
        character.setName(dto.name());
        character.setLevel(dto.level());
        character.setArmorClass(dto.armorClass());
        character.setRace(race);
        character.setCharClass(charClass);
        character.setStrength(dto.strength());
        character.setConstitution(dto.constitution());
        character.setDexterity(dto.dexterity());
        character.setIntelligence(dto.intelligence());
        character.setWisdom(dto.wisdom());
        character.setCharisma(dto.charisma());
        character.setDescription(dto.description());
        character.setHistory(dto.history());
    }

    public CharacterListResponseDto toCharacterListDto(PlayerCharacter character) {
        return new CharacterListResponseDto(
            character.getId(),
            character.getName(),
            character.getLevel(),
            character.getRace().getName(),
            character.getCharClass().getName()
        );
    }

    public List<CharacterListResponseDto> toListOfCharactersDto(List<PlayerCharacter> characters) {
        return characters.stream()
        .map(this::toCharacterListDto)
        .toList();
    }

    public CharacterDetailResponseDto toCharacterDetailDto(PlayerCharacter character) {
        List<String> spells = character.getSpells().stream()
        .map(Spell::getName)
        .toList();

        InventoryResponseDto inventoryResponseDto = null;
        Inventory inventory = character.getInventory();
        if (inventory != null) {
            List<ObjectResponseDto> objectDtos = inventory.getEntries().stream()
            .map(entry -> new ObjectResponseDto(entry.getObject().getName(),entry.getQuantity()))
            .toList();

            inventoryResponseDto = new InventoryResponseDto(
                inventory.getGold(),
                inventory.getWeapon() != null ? inventory.getWeapon().getName() :null,
                inventory.getArmor() != null ? inventory.getArmor().getName() :null,
                objectDtos
            );
        }

        return new CharacterDetailResponseDto(
            character.getId(),
            character.getName(),
            character.getLevel(),
            character.getArmorClass(),
            character.getRace().getName(),
            character.getCharClass().getName(),
            character.getStrength(),
            character.getConstitution(),
            character.getDexterity(),
            character.getIntelligence(),
            character.getWisdom(),
            character.getCharisma(),
            character.getDescription(),
            character.getHistory(),
            spells.isEmpty() ? Collections.emptyList() : spells,
            inventoryResponseDto
        );
    }
}
