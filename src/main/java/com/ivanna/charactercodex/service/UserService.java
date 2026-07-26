package com.ivanna.charactercodex.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.ivanna.charactercodex.dto.request.UserRegisterDto;
import com.ivanna.charactercodex.dto.request.UserUpdateDto;
import com.ivanna.charactercodex.dto.response.UserResponseDto;

public interface UserService {
    public UserResponseDto registerUser(UserRegisterDto userRegisterDto);

    public UserResponseDto getCurrentUser(String email);

    public UserDetails loadUserByUsername(String email);

    public UserResponseDto updateUser(String currentEmail, UserUpdateDto dto);
}
