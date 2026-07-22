package com.ivanna.charactercodex.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivanna.charactercodex.entity.Weapon;

public interface WeaponRepository extends JpaRepository<Weapon, UUID>{    
    boolean existsByName(String name);
}
