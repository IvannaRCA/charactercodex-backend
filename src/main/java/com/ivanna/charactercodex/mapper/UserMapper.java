package com.ivanna.charactercodex.mapper;

import com.ivanna.charactercodex.dto.request.UserLoginDto;
import com.ivanna.charactercodex.dto.request.UserRegisterDto;
import com.ivanna.charactercodex.entity.User;

public class UserMapper {
    public User toUserRegisterEntity(UserRegisterDto dto){
        if (dto == null) return null;
        return User.builder()
                .name(dto.name())
                .email(dto.email())
                .password(dto.password())
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
