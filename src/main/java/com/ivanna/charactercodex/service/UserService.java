package com.ivanna.charactercodex.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.ivanna.charactercodex.dto.request.UserRegisterDto;
import com.ivanna.charactercodex.entity.User;

public interface UserService {
    public User registerUser(UserRegisterDto userRegisterDto);

    public UserDetails loadUserByUsername(String email);
}
