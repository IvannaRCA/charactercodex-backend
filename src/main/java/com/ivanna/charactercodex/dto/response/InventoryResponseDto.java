package com.ivanna.charactercodex.dto.response;

import java.util.List;

public record InventoryResponseDto(
    Integer gold,
    String weaponName,
    String armorName,
    List<ObjectResponseDto> objects
) {

}
