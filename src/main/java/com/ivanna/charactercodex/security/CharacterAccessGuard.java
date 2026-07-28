package com.ivanna.charactercodex.security;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.ivanna.charactercodex.entity.PlayerCharacter;
import com.ivanna.charactercodex.entity.User;
import com.ivanna.charactercodex.exception.AccessDeniedException;
import com.ivanna.charactercodex.exception.EntityNotFoundException;
import com.ivanna.charactercodex.repository.PlayerCharacterRepository;
import com.ivanna.charactercodex.repository.UserRepository;

@Component
public class CharacterAccessGuard {
    private final PlayerCharacterRepository characterRepository;
    private final  UserRepository userRepository;

    public CharacterAccessGuard(PlayerCharacterRepository characterRepository, UserRepository userRepository) {
        this.characterRepository = characterRepository;
        this.userRepository = userRepository;
    }

    public PlayerCharacter getUserCharacter(UUID characterId, String userEmail) {
        PlayerCharacter character = characterRepository.findById(characterId)
        .orElseThrow(() -> new EntityNotFoundException(PlayerCharacter.class, characterId.toString()));

        User requester = userRepository.findByEmail(userEmail)
        .orElseThrow(() -> new EntityNotFoundException(User.class, userEmail));

        if (!character.getUser().getId().equals(requester.getId())) {
            throw new AccessDeniedException(PlayerCharacter.class);
        }

        return character;
    }
}
