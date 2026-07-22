package com.ivanna.charactercodex.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivanna.charactercodex.entity.CharClass;

public interface CharClassRepository extends JpaRepository<CharClass, UUID>{
    Optional<CharClass> findByName(String name);
    
    boolean existsByName(String name);
}
