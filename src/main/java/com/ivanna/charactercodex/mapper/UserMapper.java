package com.ivanna.charactercodex.mapper;

import com.ivanna.charactercodex.dto.request.UserLoginDto;
import com.ivanna.charactercodex.dto.request.UserRegisterDto;
import com.ivanna.charactercodex.entity.User;

public class UserMapper {
    public User toUserRegisterEntity(UserRegisterDto userRegisterDto){
        if (userRegisterDto == null) return null;
        
        User user = new User();
        user.setName(userRegisterDto.name());
        user.setEmail(userRegisterDto.email());
        user.setPassword(userRegisterDto.password());

        return user;
    }

    public User toUserLoginEntity(UserLoginDto userLoginDto){
        if (userLoginDto == null) return null;
        
        User user = new User();
        user.setEmail(userLoginDto.email());
        user.setPassword(userLoginDto.password());

        return user;
    }
}
