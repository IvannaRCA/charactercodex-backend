package com.ivanna.charactercodex.dto.response;

import java.util.List;
import java.util.UUID;

public record InventoryResponseDto(
    Integer gold,
    UUID weaponId,
    String weaponName,
    UUID armorId,
    String armorName,
    List<ObjectResponseDto> objects
) {

}
