package com.ivanna.charactercodex.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserLoginDto(
    @NotBlank(message = "email must not be blank")
    @Email(message = "email must be valid")
    String email,

    @NotBlank(message = "password must not be blank")
    @Size(min = 8, message = "password must be at least 8 characters")
    String password
) {

}
