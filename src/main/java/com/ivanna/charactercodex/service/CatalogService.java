package com.ivanna.charactercodex.service;

import java.util.List;

import com.ivanna.charactercodex.dto.response.ArmorDto;
import com.ivanna.charactercodex.dto.response.CharClassDto;
import com.ivanna.charactercodex.dto.response.InvObjectDto;
import com.ivanna.charactercodex.dto.response.RaceDto;
import com.ivanna.charactercodex.dto.response.SpellDto;
import com.ivanna.charactercodex.dto.response.WeaponDto;

public interface CatalogService {
    List<RaceDto> getRaces();

    List<CharClassDto> getClasses();

    List<WeaponDto> getWeapons();

    List<ArmorDto> getArmors();

    List<InvObjectDto> getObjects();

    List<SpellDto> getSpells();
}
