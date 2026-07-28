package com.ivanna.charactercodex.dto.response;

import java.util.UUID;

public record ArmorDto(
    UUID id,
    String name,
    String description,
    String defense
) {

}
