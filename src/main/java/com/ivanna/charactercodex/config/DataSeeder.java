package com.ivanna.charactercodex.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ivanna.charactercodex.dto.seed.ArmorSeedDto;
import com.ivanna.charactercodex.dto.seed.CharClassSeedDto;
import com.ivanna.charactercodex.dto.seed.InvObjectSeedDto;
import com.ivanna.charactercodex.dto.seed.RaceSeedDto;
import com.ivanna.charactercodex.dto.seed.SpellSeedDto;
import com.ivanna.charactercodex.dto.seed.WeaponSeedDto;
import com.ivanna.charactercodex.entity.Armor;
import com.ivanna.charactercodex.entity.CharClass;
import com.ivanna.charactercodex.entity.InvObject;
import com.ivanna.charactercodex.entity.Race;
import com.ivanna.charactercodex.entity.Role;
import com.ivanna.charactercodex.entity.Spell;
import com.ivanna.charactercodex.entity.Weapon;
import com.ivanna.charactercodex.repository.ArmorRepository;
import com.ivanna.charactercodex.repository.CharClassRepository;
import com.ivanna.charactercodex.repository.InvObjectRepository;
import com.ivanna.charactercodex.repository.RaceRepository;
import com.ivanna.charactercodex.repository.RoleRepository;
import com.ivanna.charactercodex.repository.SpellRepository;
import com.ivanna.charactercodex.repository.WeaponRepository;
import com.ivanna.charactercodex.util.SeedJsonReader;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataSeeder implements CommandLineRunner{
    private final SeedJsonReader seedJsonReader;

    private final RoleRepository roleRepository;
    private final RaceRepository raceRepository;
    private final CharClassRepository charClassRepository;
    private final ArmorRepository armorRepository;
    private final WeaponRepository weaponRepository;
    private final InvObjectRepository invObjectRepository;
    private final SpellRepository spellRepository;

    @Override
    public void run(String... args){
        seedRoles();
        seedRaces();
        seedClasses();
        seedArmors();
        seedWeapons();
        seedObjects();
        seedSpells();
    }


    private void seedRoles() {
        List<String> defaultRoles = List.of("USER", "MASTER");
        for (String roleName : defaultRoles) {
            if (!roleRepository.existsByName(roleName)) {
                roleRepository.save(Role.builder().name(roleName).build());
                log.info("Seeded role: {}", roleName);
            }
        }
    }

    private void seedRaces() {
        List<RaceSeedDto> races = seedJsonReader.readList("races.json", RaceSeedDto.class);
        for (RaceSeedDto dto : races) {
            if (!raceRepository.existsByName(dto.name())) {
                raceRepository.save(Race.builder()
                        .name(dto.name())
                        .description(dto.description())
                        .build());
                log.info("Seeded race: {}", dto.name());
            }
        }
    }

    private void seedClasses() {
        List<CharClassSeedDto> classes = seedJsonReader.readList("classes.json", CharClassSeedDto.class);
        for (CharClassSeedDto dto : classes) {
            if (!charClassRepository.existsByName(dto.name())) {
                charClassRepository.save(CharClass.builder()
                        .name(dto.name())
                        .description(dto.description())
                        .build());
                log.info("Seeded class: {}", dto.name());
            }
        }
    }

    private void seedWeapons() {
        List<WeaponSeedDto> weapons = seedJsonReader.readList("weapons.json", WeaponSeedDto.class);
        for (WeaponSeedDto dto : weapons) {
            if (!weaponRepository.existsByName(dto.name())) {
                weaponRepository.save(Weapon.builder()
                        .name(dto.name())
                        .description(dto.description())
                        .damage(dto.damage())
                        .build());
                log.info("Seeded weapon: {}", dto.name());
            }
        }
    }

    private void seedArmors() {
        List<ArmorSeedDto> armors = seedJsonReader.readList("armors.json", ArmorSeedDto.class);
        for (ArmorSeedDto dto : armors) {
            if (!armorRepository.existsByName(dto.name())) {
                armorRepository.save(Armor.builder()
                        .name(dto.name())
                        .description(dto.description())
                        .defense(dto.defense())
                        .build());
                log.info("Seeded armor: {}", dto.name());
            }
        }
    }

    private void seedObjects() {
        List<InvObjectSeedDto> objects = seedJsonReader.readList("objects.json", InvObjectSeedDto.class);
        for (InvObjectSeedDto dto : objects) {
            if (!invObjectRepository.existsByName(dto.name())) {
                invObjectRepository.save(InvObject.builder()
                        .name(dto.name())
                        .description(dto.description())
                        .price(dto.price())
                        .build());
                log.info("Seeded object: {}", dto.name());
            }
        }
    }

    private void seedSpells() {
        List<SpellSeedDto> spells = seedJsonReader.readList("spells.json", SpellSeedDto.class);
        for (SpellSeedDto dto : spells) {
            if (!spellRepository.existsByName(dto.name())) {
                spellRepository.save(Spell.builder()
                        .name(dto.name())
                        .description(dto.description())
                        .level(dto.level())
                        .build());
                log.info("Seeded spell: {}", dto.name());
            }
        }
    }
}
