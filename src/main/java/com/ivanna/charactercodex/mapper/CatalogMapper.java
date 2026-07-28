package com.ivanna.charactercodex.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ivanna.charactercodex.dto.response.ArmorDto;
import com.ivanna.charactercodex.dto.response.CharClassDto;
import com.ivanna.charactercodex.dto.response.InvObjectDto;
import com.ivanna.charactercodex.dto.response.RaceDto;
import com.ivanna.charactercodex.dto.response.SpellDto;
import com.ivanna.charactercodex.dto.response.WeaponDto;
import com.ivanna.charactercodex.entity.Armor;
import com.ivanna.charactercodex.entity.CharClass;
import com.ivanna.charactercodex.entity.InvObject;
import com.ivanna.charactercodex.entity.Race;
import com.ivanna.charactercodex.entity.Spell;
import com.ivanna.charactercodex.entity.Weapon;

@Component
public class CatalogMapper {
    public RaceDto toDto(Race race) {
        return new RaceDto(race.getId(), race.getName(), race.getDescription());
    }

    public List<RaceDto> toRaceListDto(List<Race> races) {
        return races.stream().map(this::toDto).toList();
    }

    public CharClassDto toDto(CharClass charClass) {
        return new CharClassDto(charClass.getId(), charClass.getName(), charClass.getDescription());
    }

    public List<CharClassDto> toClassListDto(List<CharClass> classes) {
        return classes.stream().map(this::toDto).toList();
    }

    public WeaponDto toDto(Weapon weapon) {
        return new WeaponDto(weapon.getId(), weapon.getName(), weapon.getDescription(), weapon.getDamage());
    }

    public List<WeaponDto> toWeaponListDto(List<Weapon> weapons) {
        return weapons.stream().map(this::toDto).toList();
    }

    public ArmorDto toDto(Armor armor) {
        return new ArmorDto(armor.getId(), armor.getName(), armor.getDescription(), armor.getDefense());
    }

    public List<ArmorDto> toArmorListDto(List<Armor> armors) {
        return armors.stream().map(this::toDto).toList();
    }

    public InvObjectDto toDto(InvObject object) {
        return new InvObjectDto(object.getId(), object.getName(), object.getDescription(), object.getPrice());
    }

    public List<InvObjectDto> toObjectListDto(List<InvObject> objects) {
        return objects.stream().map(this::toDto).toList();
    }

    public SpellDto toDto(Spell spell) {
        return new SpellDto(spell.getId(), spell.getName(), spell.getDescription(), spell.getLevel());
    }

    public List<SpellDto> toSpellListDto(List<Spell> spells) {
        return spells.stream().map(this::toDto).toList();
    }
}
