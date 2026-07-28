package com.ivanna.charactercodex.dto.response;

import java.util.UUID;

public record RaceDto(
    UUID id,
    String name,
    String description
) {

}
