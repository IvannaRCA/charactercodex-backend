package com.ivanna.charactercodex.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivanna.charactercodex.entity.Spell;

public interface SpellRepository extends JpaRepository<Spell, UUID>{
    boolean existsByName(String name);
}
