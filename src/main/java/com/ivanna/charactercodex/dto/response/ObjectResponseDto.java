package com.ivanna.charactercodex.dto.response;

import java.util.UUID;

public record ObjectResponseDto(
    UUID objectId,
    String objectName,
    Integer quantity
) {

}
