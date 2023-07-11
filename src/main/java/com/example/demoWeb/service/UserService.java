package com.example.demoWeb.service;

import com.example.demoWeb.dto.RegistrationDto;
import com.example.demoWeb.model.UserEntity;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);

    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);
}
