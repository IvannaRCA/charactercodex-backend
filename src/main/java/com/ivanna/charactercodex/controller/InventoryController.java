package com.ivanna.charactercodex.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ivanna.charactercodex.dto.request.AddObjectDto;
import com.ivanna.charactercodex.dto.request.EquipArmorDto;
import com.ivanna.charactercodex.dto.request.EquipWeaponDto;
import com.ivanna.charactercodex.dto.request.UpdateGoldDto;
import com.ivanna.charactercodex.dto.request.UpdateQuantityDto;
import com.ivanna.charactercodex.dto.response.CharacterDetailResponseDto;
import com.ivanna.charactercodex.service.InventoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/characters/{characterId/inventory}")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PutMapping("/weapon")
    public ResponseEntity<CharacterDetailResponseDto> equipWeapon(@PathVariable UUID characterId,
        @Valid @RequestBody EquipWeaponDto dto,
        Authentication authentication) {
        
        return new ResponseEntity<>(inventoryService.equipWeapon(characterId, dto.weaponId(), authentication.getName()), HttpStatus.OK);
    }

    @DeleteMapping("/weapon")
    public ResponseEntity<CharacterDetailResponseDto> unequipWeapon(
        @PathVariable UUID characterId,
        Authentication authentication) {
        
        return new ResponseEntity<>(inventoryService.unequipWeapon(characterId, authentication.getName()), HttpStatus.OK);
    }

    @PutMapping("/armor")
    public ResponseEntity<CharacterDetailResponseDto> equipArmor(
        @PathVariable UUID characterId,
        @Valid @RequestBody EquipArmorDto dto,
        Authentication authentication) {
        
        return new ResponseEntity<>(inventoryService.equipArmor(characterId, dto.armorId(), authentication.getName()), HttpStatus.OK);
    }

    @DeleteMapping("/armor")
    public ResponseEntity<CharacterDetailResponseDto> unequipArmor(
        @PathVariable UUID characterId,
        Authentication authentication) {
        
        return new ResponseEntity<>(inventoryService.unequipArmor(characterId, authentication.getName()), HttpStatus.OK);
    }

    @PostMapping("/objects")
    public ResponseEntity<CharacterDetailResponseDto> addObject(
        @PathVariable UUID characterId,
        @Valid @RequestBody AddObjectDto dto,
        Authentication authentication) {
        
        return new ResponseEntity<>(inventoryService.addObject(characterId, dto.objectId(), dto.quantity(), authentication.getName()), HttpStatus.OK);
    }

    @PutMapping("/objects/{objectId}")
    public ResponseEntity<CharacterDetailResponseDto> updateObjectQuantity(
        @PathVariable UUID characterId,
        @PathVariable UUID objectId,
        @Valid @RequestBody UpdateQuantityDto dto,
        Authentication authentication) {
        
        return new ResponseEntity<>(inventoryService.updateObjectQuantity(characterId, objectId, dto.quantity(), authentication.getName()), HttpStatus.OK);
    }

    @DeleteMapping("/objects/{objectId}")
    public ResponseEntity<CharacterDetailResponseDto> removeObject(
        @PathVariable UUID characterId,
        @PathVariable UUID objectId,
        Authentication authentication) {
        
        return new ResponseEntity<>(inventoryService.removeObject(characterId, objectId, authentication.getName()), HttpStatus.OK);
    }

    @PutMapping("/gold")
    public ResponseEntity<CharacterDetailResponseDto> updateGold(
        @PathVariable UUID characterId,
        @Valid @RequestBody UpdateGoldDto dto,
        Authentication authentication) {
        
        return new ResponseEntity<>(inventoryService.updateGold(characterId, dto.gold(), authentication.getName()), HttpStatus.OK);
    }
}
