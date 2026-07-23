package com.ivanna.charactercodex.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ivanna.charactercodex.dto.request.UserRegisterDto;
import com.ivanna.charactercodex.entity.User;
import com.ivanna.charactercodex.mapper.UserMapper;
import com.ivanna.charactercodex.repository.UserRepository;
import com.ivanna.charactercodex.security.UserDetail;

@Service
public class UserServiceImp implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImp(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public User registerUser(UserRegisterDto userRegisterDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'registerUser'");
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
        .map(user -> new UserDetail(user))
        .orElseThrow();
    }

}
