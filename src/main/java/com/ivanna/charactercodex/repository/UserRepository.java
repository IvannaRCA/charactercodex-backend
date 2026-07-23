package com.ivanna.charactercodex.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivanna.charactercodex.entity.User;

public interface UserRepository extends JpaRepository<User, UUID>{
    public Optional<User> findByEmail(String email);
}
