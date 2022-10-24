package com.gbrramos.lix.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gbrramos.lix.models.User;
import com.gbrramos.lix.models.UserDto;
import com.gbrramos.lix.repositories.IUserRepository;

@RestController
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IUserRepository userRepository;

    UserController(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    private UserDto geraUsuarioBase() throws Exception {
        
        User user = new User();
        user.setName("Admin");
        user.setEmail("admin@base.com");
        user.setRole("base");
        user.setPassword(passwordEncoder.encode("123456"));
        
        userRepository.save(user);

        UserDto res = new UserDto();
        res.setEmail(user.getEmail());
        res.setName(user.getName());
        res.setRole(user.getRole());

        return res;
    }
}
