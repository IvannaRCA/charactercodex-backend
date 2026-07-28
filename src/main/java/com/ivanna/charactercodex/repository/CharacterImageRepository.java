package com.ivanna.charactercodex.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivanna.charactercodex.entity.CharacterImage;

public interface CharacterImageRepository extends JpaRepository<CharacterImage, UUID>{
    Optional<CharacterImage> findByCharacterId(UUID characterId);
}
