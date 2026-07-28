package com.ivanna.charactercodex.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivanna.charactercodex.entity.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, UUID>{
    Optional<Inventory> findByCharacterId(UUID characterId);
}
