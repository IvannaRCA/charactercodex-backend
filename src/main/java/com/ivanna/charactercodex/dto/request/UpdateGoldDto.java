package com.ivanna.charactercodex.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record UpdateGoldDto(
    @NotNull
    @Min(0)
    Integer gold
) {

}
