package com.ivanna.charactercodex.mapper;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ivanna.charactercodex.dto.request.UserLoginDto;
import com.ivanna.charactercodex.dto.request.UserRegisterDto;
import com.ivanna.charactercodex.entity.User;

public class UserMapper {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserMapper(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User toUserRegisterEntity(UserRegisterDto dto){
        if (dto == null) return null;
        return User.builder()
                .name(dto.name())
                .email(dto.email())
                .password(bCryptPasswordEncoder.encode(dto.password()))
                .build();
    }

    public User toUserLoginEntity(UserLoginDto dto){
        if (dto == null) return null;
        return User.builder()
                .email(dto.email())
                .password(dto.password())
                .build();
    }
}
