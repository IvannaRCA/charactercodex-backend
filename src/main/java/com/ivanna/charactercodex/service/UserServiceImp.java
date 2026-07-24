package com.ivanna.charactercodex.service;

import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ivanna.charactercodex.dto.request.UserRegisterDto;
import com.ivanna.charactercodex.dto.response.UserResponseDto;
import com.ivanna.charactercodex.entity.Role;
import com.ivanna.charactercodex.entity.User;
import com.ivanna.charactercodex.exception.DuplicateResourceException;
import com.ivanna.charactercodex.exception.EntityNotFoundException;
import com.ivanna.charactercodex.mapper.UserMapper;
import com.ivanna.charactercodex.repository.RoleRepository;
import com.ivanna.charactercodex.repository.UserRepository;
import com.ivanna.charactercodex.security.UserDetail;

@Service
public class UserServiceImp implements UserService, UserDetailsService {

    private static final String DEFAULT_ROLE = "USER";
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImp(UserRepository userRepository, UserMapper userMapper, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponseDto registerUser(UserRegisterDto userRegisterDto) {
        if (userRepository.existsByEmail(userRegisterDto.email())) {
            throw new DuplicateResourceException(userRegisterDto.email());
        }

        Role userRole = roleRepository.findByName(DEFAULT_ROLE)
                .orElseThrow(() -> new IllegalStateException(
                        "Default role not seeded: " + DEFAULT_ROLE));


        User user = userMapper.toUserRegisterEntity(userRegisterDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Set.of(userRole));

        return userMapper.toUserResponseDto(userRepository.save(user));
    }

    @Override
    public UserResponseDto getCurrentUser(String email) {
        User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new EntityNotFoundException(User.class, email));
        return userMapper.toUserResponseDto(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
        .map(user -> new UserDetail(user))
        .orElseThrow(() -> new EntityNotFoundException(User.class, email));
    }


}
