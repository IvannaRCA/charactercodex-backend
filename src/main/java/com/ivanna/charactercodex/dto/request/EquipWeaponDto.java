package com.ivanna.charactercodex.dto.request;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record EquipWeaponDto(
    @NotNull
    UUID weaponId
) {

}
