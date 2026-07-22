package com.ivanna.charactercodex.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivanna.charactercodex.entity.InvObject;

public interface InvObjectRepository extends JpaRepository<InvObject, UUID>{
    boolean eexistsByName(String name);
}
