package com.ivanna.charactercodex.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivanna.charactercodex.entity.Race;

public interface RaceRepository extends JpaRepository<Race, UUID>{
    Optional<Race> findByName(String name);
    
    boolean existsByName(String name);
}
