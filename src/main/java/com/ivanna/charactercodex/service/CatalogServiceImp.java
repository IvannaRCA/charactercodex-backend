package com.ivanna.charactercodex.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ivanna.charactercodex.dto.response.ArmorDto;
import com.ivanna.charactercodex.dto.response.CharClassDto;
import com.ivanna.charactercodex.dto.response.InvObjectDto;
import com.ivanna.charactercodex.dto.response.RaceDto;
import com.ivanna.charactercodex.dto.response.SpellDto;
import com.ivanna.charactercodex.dto.response.WeaponDto;
import com.ivanna.charactercodex.mapper.CatalogMapper;
import com.ivanna.charactercodex.repository.ArmorRepository;
import com.ivanna.charactercodex.repository.CharClassRepository;
import com.ivanna.charactercodex.repository.InvObjectRepository;
import com.ivanna.charactercodex.repository.RaceRepository;
import com.ivanna.charactercodex.repository.SpellRepository;
import com.ivanna.charactercodex.repository.WeaponRepository;

@Service
public class CatalogServiceImp implements CatalogService {
    
    private final RaceRepository raceRepository;
    private final CharClassRepository charClassRepository;
    private final WeaponRepository weaponRepository;
    private final ArmorRepository armorRepository;
    private final InvObjectRepository invObjectRepository;
    private final SpellRepository spellRepository;
    private final CatalogMapper catalogMapper;
    
    public CatalogServiceImp(RaceRepository raceRepository,CharClassRepository charClassRepository, WeaponRepository weaponRepository, ArmorRepository armorRepository,InvObjectRepository invObjectRepository, SpellRepository spellRepository, CatalogMapper catalogMapper) {
        this.raceRepository = raceRepository;
        this.charClassRepository = charClassRepository;
        this.weaponRepository = weaponRepository;
        this.armorRepository = armorRepository;
        this.invObjectRepository = invObjectRepository;
        this.spellRepository = spellRepository;
        this.catalogMapper = catalogMapper;
    }

    @Override
    public List<RaceDto> getRaces() {
        return catalogMapper.toRaceListDto(raceRepository.findAll());
    }

    @Override
    public List<CharClassDto> getClasses() {
        return catalogMapper.toClassListDto(charClassRepository.findAll());
    }

    @Override
    public List<WeaponDto> getWeapons() {
        return catalogMapper.toWeaponListDto(weaponRepository.findAll());
    }

    @Override
    public List<ArmorDto> getArmors() {
        return catalogMapper.toArmorListDto(armorRepository.findAll());
    }

    @Override
    public List<InvObjectDto> getObjects() {
        return catalogMapper.toObjectListDto(invObjectRepository.findAll());
    }

    @Override
    public List<SpellDto> getSpells() {
        return catalogMapper.toSpellListDto(spellRepository.findAll());
    }

}
