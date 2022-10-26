package com.gbrramos.lix.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gbrramos.lix.models.User;
import com.gbrramos.lix.repositories.IUserRepository;

@Controller
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IUserRepository userRepository;

    @Bean
    private void geraUsuarioBase() {
        
        List<User> lUsers = userRepository.findByEmail("admin@base.com");

        if(lUsers.size() <= 0) {
            User user = new User();
            user.setName("Admin");
            user.setEmail("admin@base.com");
            user.setRole("base");
            user.setPassword(passwordEncoder.encode("123456"));
            
            userRepository.save(user);
        }

    }

    @GetMapping
    public ResponseEntity<?> listUsers() {
        List<User> lUsers = userRepository.findAll();
        return new ResponseEntity<List<User>>(lUsers, null, 200);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable long id) {
        Optional<User> user = userRepository.findById(id);
        return new ResponseEntity<Optional<User>>(user, null, 200);
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody User regUser) {
        User user = new User();

        user.setEmail(regUser.getEmail());
        user.setName(regUser.getName());
        user.setPassword(passwordEncoder.encode(regUser.getPassword()));
        user.setCompany_id(1);
        user.setRole(regUser.getRole());

        userRepository.save(user);

        return new ResponseEntity<String>("ok", null, 200);
    }
}
