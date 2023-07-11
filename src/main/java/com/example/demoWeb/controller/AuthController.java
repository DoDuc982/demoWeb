package com.example.demoWeb.controller;

import com.example.demoWeb.dto.RegistrationDto;
import com.example.demoWeb.model.UserEntity;
import com.example.demoWeb.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    private final UserService userService;
    public AuthController (UserService userService){
        this.userService = userService;
    }
    @GetMapping("/register")
    public String getRegisterForm(Model model){
        RegistrationDto user = new RegistrationDto();
        model.addAttribute("user", user);
        return "register";
    }
    @GetMapping("/login")
    public String loginPage(){
        return "/login";
    }
    @PostMapping("/register/save")
    public String register(
            @Valid @ModelAttribute("User") RegistrationDto user,
            BindingResult result,
            Model model
    ){
        UserEntity existingUserEmail = userService.findByEmail(user.getEmail());
        if (existingUserEmail != null && existingUserEmail.getEmail() != null && !existingUserEmail.getEmail().isEmpty()){
            return "redirect:/register?fail";
        }
        UserEntity existingUsername = userService.findByUsername(user.getUsername());
        if (existingUsername != null && existingUsername.getUsername() != null && !existingUsername.getUsername().isEmpty()){
            return "redirect:/register?fail";
        }
        if (result.hasErrors()){
            model.addAttribute("user", user);
            return "register";
        }
        userService.saveUser(user);
        return "redirect:/clubs?success";
    }
}