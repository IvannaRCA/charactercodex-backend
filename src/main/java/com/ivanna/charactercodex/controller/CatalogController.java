package com.ivanna.charactercodex.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ivanna.charactercodex.dto.response.ArmorDto;
import com.ivanna.charactercodex.dto.response.CharClassDto;
import com.ivanna.charactercodex.dto.response.InvObjectDto;
import com.ivanna.charactercodex.dto.response.RaceDto;
import com.ivanna.charactercodex.dto.response.SpellDto;
import com.ivanna.charactercodex.dto.response.WeaponDto;
import com.ivanna.charactercodex.service.CatalogService;

@RestController
@RequestMapping("/api/v1")
public class CatalogController {

    private final CatalogService catalogService;

    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping("/races")
    public ResponseEntity<List<RaceDto>> getRaces() {
        return new ResponseEntity<>(catalogService.getRaces(), HttpStatus.OK);
    }

    @GetMapping("/classes")
    public ResponseEntity<List<CharClassDto>> getClasses() {
        return new ResponseEntity<>(catalogService.getClasses(), HttpStatus.OK);
    }

    @GetMapping("/armors")
    public ResponseEntity<List<ArmorDto>> getArmors() {
        return new ResponseEntity<>(catalogService.getArmors(), HttpStatus.OK);
    }

    @GetMapping("/weapons")
    public ResponseEntity<List<WeaponDto>> getWeapon() {
        return new ResponseEntity<>(catalogService.getWeapons(), HttpStatus.OK);
    }

    @GetMapping("/objects")
    public ResponseEntity<List<InvObjectDto>> getObjects() {
        return new ResponseEntity<>(catalogService.getObjects(), HttpStatus.OK);
    }

    @GetMapping("/spells")
    public ResponseEntity<List<SpellDto>> getSpells() {
        return new ResponseEntity<>(catalogService.getSpells(), HttpStatus.OK);
    }
}
