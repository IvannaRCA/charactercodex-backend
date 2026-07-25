package com.ivanna.charactercodex.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserUpdateDto(
    @NotBlank(message = "name must not be blank")
    @Size(min = 3, max = 100, message = "name must be between 3 and 100 characters")
    String name,

    @NotBlank(message = "email must not be blank")
    @Email(message = "email must be valid")
    String email
) {

}
