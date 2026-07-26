package com.ivanna.charactercodex.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivanna.charactercodex.entity.PlayerCharacter;

public interface PlayerCharacterRepository extends JpaRepository<PlayerCharacter, UUID>{
    List<PlayerCharacter> findAllByUserId(UUID id);
}
