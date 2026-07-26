package com.ivanna.charactercodex.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ivanna.charactercodex.dto.request.UserRegisterDto;
import com.ivanna.charactercodex.dto.request.UserUpdateDto;
import com.ivanna.charactercodex.dto.response.UserResponseDto;
import com.ivanna.charactercodex.service.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/v1/auth")
public class UserController {
    
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@Valid @RequestBody UserRegisterDto dto) {
        return new ResponseEntity<>(userService.registerUser(dto), HttpStatus.CREATED);
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> me(Authentication authentication) {
        return new ResponseEntity<>(userService.getCurrentUser(authentication.getName()), HttpStatus.OK);
    }
    
    @PutMapping("/me")
    public ResponseEntity<UserResponseDto> updateMe(Authentication authentication, @Valid @RequestBody UserUpdateDto dto) {
        return new ResponseEntity<>(userService.updateUser(authentication.getName(), dto), HttpStatus.OK);
    }

}
