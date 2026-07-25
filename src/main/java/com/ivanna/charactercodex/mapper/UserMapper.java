package com.ivanna.charactercodex.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ivanna.charactercodex.dto.request.UserLoginDto;
import com.ivanna.charactercodex.dto.request.UserRegisterDto;
import com.ivanna.charactercodex.dto.request.UserUpdateDto;
import com.ivanna.charactercodex.dto.response.UserResponseDto;
import com.ivanna.charactercodex.entity.Role;
import com.ivanna.charactercodex.entity.User;

@Component
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

    public UserResponseDto toUserResponseDto(User user){
        List<String> roleNames = user.getRoles().stream()
            .map(Role::getName)
            .toList();

        return new UserResponseDto(
            user.getId(),
            user.getName(),
            user.getEmail(),
            roleNames
        );
    }

    public void toUserUpdateEntity(User user, UserUpdateDto dto) {
        user.setName(dto.name());
        user.setEmail(dto.email());
    }
}
