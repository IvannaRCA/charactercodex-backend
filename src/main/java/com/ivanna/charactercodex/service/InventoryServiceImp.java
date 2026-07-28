package com.ivanna.charactercodex.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ivanna.charactercodex.dto.response.CharacterDetailResponseDto;
import com.ivanna.charactercodex.entity.Armor;
import com.ivanna.charactercodex.entity.InvObject;
import com.ivanna.charactercodex.entity.Inventory;
import com.ivanna.charactercodex.entity.InventoryEntry;
import com.ivanna.charactercodex.entity.PlayerCharacter;
import com.ivanna.charactercodex.entity.Weapon;
import com.ivanna.charactercodex.exception.EntityNotFoundException;
import com.ivanna.charactercodex.mapper.CharacterMapper;
import com.ivanna.charactercodex.repository.ArmorRepository;
import com.ivanna.charactercodex.repository.InvObjectRepository;
import com.ivanna.charactercodex.repository.InventoryEntryRepository;
import com.ivanna.charactercodex.repository.WeaponRepository;
import com.ivanna.charactercodex.security.CharacterAccessGuard;

@Service
public class InventoryServiceImp implements InventoryService {
    
    private final WeaponRepository weaponRepository;
    private final ArmorRepository armorRepository;
    private final InvObjectRepository invObjectRepository;
    private final InventoryEntryRepository inventoryEntryRepository;
    private final CharacterAccessGuard characterAccessGuard;
    private final CharacterMapper characterMapper;
    
    public InventoryServiceImp(WeaponRepository weaponRepository, ArmorRepository armorRepository, InvObjectRepository invObjectRepository, InventoryEntryRepository inventoryEntryRepository, CharacterAccessGuard characterAccessGuard, CharacterMapper characterMapper) {
        this.weaponRepository = weaponRepository;
        this.armorRepository = armorRepository;
        this.invObjectRepository = invObjectRepository;
        this.inventoryEntryRepository = inventoryEntryRepository;
        this.characterAccessGuard = characterAccessGuard;
        this.characterMapper = characterMapper;
    }


    @Override
    public CharacterDetailResponseDto equipWeapon(UUID characterId, UUID weaponId, String userEmail) {
        PlayerCharacter character = characterAccessGuard.getUserCharacter(characterId, userEmail);
        Weapon weapon = weaponRepository.findById(weaponId)
        .orElseThrow(() -> new EntityNotFoundException(Weapon.class));

        character.getInventory().setWeapon(weapon);
        return characterMapper.toCharacterDetailDto(character);
    }

    @Override
    public CharacterDetailResponseDto unequipWeapon(UUID characterId, String userEmail) {
        PlayerCharacter character = characterAccessGuard.getUserCharacter(characterId, userEmail);

        character.getInventory().setWeapon(null);
        return characterMapper.toCharacterDetailDto(character);
    }

    @Override
    public CharacterDetailResponseDto equipArmor(UUID characterId, UUID armorId, String userEmail) {
        PlayerCharacter character = characterAccessGuard.getUserCharacter(characterId, userEmail);
        Armor armor = armorRepository.findById(armorId)
        .orElseThrow(() -> new EntityNotFoundException(Armor.class));

        character.getInventory().setArmor(armor);
        return characterMapper.toCharacterDetailDto(character);
    }

    @Override
    public CharacterDetailResponseDto unequipArmor(UUID characterId, String userEmail) {
        PlayerCharacter character = characterAccessGuard.getUserCharacter(characterId, userEmail);

        character.getInventory().setWeapon(null);
        return characterMapper.toCharacterDetailDto(character);
    }

    @Override
    public CharacterDetailResponseDto addObject(UUID characterId, UUID objectId, Integer quantity, String userEmail) {
        PlayerCharacter character = characterAccessGuard.getUserCharacter(characterId, userEmail);
        Inventory inventory = character.getInventory();

        InvObject object = invObjectRepository.findById(objectId)
        .orElseThrow(() -> new EntityNotFoundException(InvObject.class, objectId.toString()));

        InventoryEntry entry = inventoryEntryRepository
        .findByInventoryIdAndObjectId(inventory.getId(), objectId)
        .map(existing -> {
            existing.setQuantity(existing.getQuantity() + quantity);
            return existing;
        })
        .orElseGet(() -> InventoryEntry.builder()
            .inventory(inventory)
            .object(object)
            .quantity(quantity)
            .build());

        inventoryEntryRepository.save(entry);
        return characterMapper.toCharacterDetailDto(character);
    }

    @Override
    public CharacterDetailResponseDto updateObjectQuantity(UUID characterId, UUID objectId, Integer quantity, String userEmail) {
        PlayerCharacter character = characterAccessGuard.getUserCharacter(characterId, userEmail);
        Inventory inventory = character.getInventory();

        InventoryEntry entry = inventoryEntryRepository
            .findByInventoryIdAndObjectId(inventory.getId(), objectId)
            .orElseThrow(() -> new EntityNotFoundException(InventoryEntry.class, objectId.toString()));

        if (quantity == 0) {
            inventoryEntryRepository.delete(entry);
        } else {
            entry.setQuantity(quantity);
            inventoryEntryRepository.save(entry);
        }

        return characterMapper.toCharacterDetailDto(character);
    }

    @Override
    public CharacterDetailResponseDto removeObject(UUID characterId, UUID objectId, String userEmail) {
        PlayerCharacter character = characterAccessGuard.getUserCharacter(characterId, userEmail);
        Inventory inventory = character.getInventory();

        InventoryEntry entry = inventoryEntryRepository
            .findByInventoryIdAndObjectId(inventory.getId(), objectId)
            .orElseThrow(() -> new EntityNotFoundException(InventoryEntry.class, objectId.toString()));

        inventoryEntryRepository.delete(entry);
        return characterMapper.toCharacterDetailDto(character);
    }

    @Override
    public CharacterDetailResponseDto updateGold(UUID characterId, Integer gold, String userEmail) {
        PlayerCharacter character = characterAccessGuard.getUserCharacter(characterId, userEmail);
        character.getInventory().setGold(gold);
        return characterMapper.toCharacterDetailDto(character);
    }
}
