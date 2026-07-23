package com.ivanna.charactercodex.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivanna.charactercodex.entity.Armor;

public interface ArmorRepository extends JpaRepository<Armor, UUID>{
    boolean existsByName(String name);
}
