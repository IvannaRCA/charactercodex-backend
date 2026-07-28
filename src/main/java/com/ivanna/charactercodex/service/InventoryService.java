package com.ivanna.charactercodex.service;

import java.util.UUID;

import com.ivanna.charactercodex.dto.response.CharacterDetailResponseDto;

public interface InventoryService {

    CharacterDetailResponseDto equipWeapon(UUID characterId, UUID weaponId, String userEmail);
    
    CharacterDetailResponseDto unequipWeapon(UUID characterId, String userEmail);

    CharacterDetailResponseDto equipArmor(UUID characterId, UUID armorId, String userEmail);
    
    CharacterDetailResponseDto unequipArmor(UUID characterId, String userEmail);

    CharacterDetailResponseDto addObject(UUID characterId, UUID objectId, Integer quantity, String userEmail);
    
    CharacterDetailResponseDto updateObjectQuantity(UUID characterId, UUID objectId, Integer quantity, String userEmail);
    
    CharacterDetailResponseDto removeObject(UUID characterId, UUID objectId, String userEmail);

    CharacterDetailResponseDto updateGold(UUID characterId, Integer gold, String userEmail);
}
