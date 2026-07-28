package com.ivanna.charactercodex.dto.request;

import java.util.UUID;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record AddObjectDto(
    @NotNull
    UUID objectId,

    @NotNull
    @Min(1)
    Integer quantity
) {

}
