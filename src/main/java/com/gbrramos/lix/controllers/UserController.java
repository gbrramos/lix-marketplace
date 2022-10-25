package com.gbrramos.lix.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gbrramos.lix.models.User;
import com.gbrramos.lix.models.UserDto;
import com.gbrramos.lix.repositories.IUserRepository;

@Controller
@RestController
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IUserRepository userRepository;

    @Bean
    private void geraUsuarioBase() {
        
        User user = new User();
        user.setName("Admin");
        user.setEmail("admin@base.com");
        user.setRole("base");
        user.setPassword(passwordEncoder.encode("123456"));
        
        userRepository.save(user);

    }
}
