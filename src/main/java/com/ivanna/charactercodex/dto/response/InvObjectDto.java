package com.ivanna.charactercodex.dto.response;

import java.util.UUID;

public record InvObjectDto(
    UUID id,
    String name,
    String description,
    Integer price
) {

}
