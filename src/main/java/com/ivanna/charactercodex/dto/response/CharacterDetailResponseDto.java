package com.ivanna.charactercodex.dto.response;

import java.util.List;
import java.util.UUID;

public record CharacterDetailResponseDto(
    UUID id,
    String name,
    Integer level,
    Integer armorClass,
    String raceName,
    String className,
    Integer strength,
    Integer constitution,
    Integer dexterity,
    Integer intelligence,
    Integer wisdom,
    Integer charisma,
    String description,
    String history,
    List<String> spellNames,
    InventoryResponseDto inventory
) {

}
