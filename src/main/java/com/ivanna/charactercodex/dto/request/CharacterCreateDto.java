package com.ivanna.charactercodex.dto.request;

import java.util.UUID;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CharacterCreateDto(

    @NotBlank(message = "name must not be blank")
    @Size(max = 100, message = "name must be at most 100 characters")
    String name,

    @NotNull(message = "level must not be null")
    @Min(value = 1, message = "level must be at least 1")
    Integer level,

    @NotNull(message = "AC must not be null")
    @Min(value = 0, message = "AC must be at least 0")
    Integer armorClass,

    @NotNull(message = "race_id must not be null")
    UUID raceId,

    @NotNull(message = "class_id must not be null")
    UUID classId,

    @NotNull @Min(1) @Max(30) Integer strength,
    @NotNull @Min(1) @Max(30) Integer constitution,
    @NotNull @Min(1) @Max(30) Integer dexterity,
    @NotNull @Min(1) @Max(30) Integer intelligence,
    @NotNull @Min(1) @Max(30) Integer wisdom,
    @NotNull @Min(1) @Max(30) Integer charisma,

    String description,
    String history
) {

}
