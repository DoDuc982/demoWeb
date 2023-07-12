package com.example.demoWeb.service.implement;

import com.example.demoWeb.dto.RegistrationDto;
import com.example.demoWeb.model.Role;
import com.example.demoWeb.model.UserEntity;
import com.example.demoWeb.repository.RoleRepository;
import com.example.demoWeb.repository.UserRepository;
import com.example.demoWeb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public UserServiceImp(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder){
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public void saveUser(RegistrationDto registrationDto) {
        UserEntity user = new UserEntity();
        user.setUsername(registrationDto.getUsername());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword())) ;
        Role role = roleRepository.findByName("USER");
        user.setRoles(Collections.singletonList(role));
        userRepository.save(user);
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
