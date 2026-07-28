package com.ivanna.charactercodex.dto.response;

import java.util.UUID;

public record WeaponDto(
    UUID id,
    String name,
    String description,
    String damage
) {

}
