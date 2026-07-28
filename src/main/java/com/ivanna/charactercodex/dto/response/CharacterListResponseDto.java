package com.ivanna.charactercodex.dto.response;

import java.util.UUID;

public record CharacterListResponseDto(
    UUID id,
    String name,
    Integer level,
    String raceName,
    String className
) {
}
