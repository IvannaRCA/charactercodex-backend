package com.ivanna.charactercodex.dto.response;

import java.util.UUID;

public record CharClassDto(
    UUID id,
    String name,
    String description
) {

}
