package com.ivanna.charactercodex.dto.request;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record AddSpellDto(
    @NotNull
    UUID spellId
) {

}
