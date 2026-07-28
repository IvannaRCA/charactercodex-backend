package com.ivanna.charactercodex.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivanna.charactercodex.entity.InventoryEntry;

public interface InventoryEntryRepository extends JpaRepository<InventoryEntry, UUID>{
    List<InventoryEntry> findAllByInventoryId(UUID inventoryId);
    
    Optional<InventoryEntry> findByInventoryIdAndObjectId(UUID inventoryId, UUID objectId);
}
