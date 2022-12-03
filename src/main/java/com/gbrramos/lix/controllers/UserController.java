package com.gbrramos.lix.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gbrramos.lix.models.JsonResponse;
import com.gbrramos.lix.models.User;
import com.gbrramos.lix.models.UserDTO;
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
    private void createBaseUser() {

        /**
         *         This function is a Bean because it need to run when the code is
         *         executed
         *         This function creates a user with the following properties:
         *         {
         *         name: "Admin",
         *         email: "admin@base.com",
         *         role: "base",
         *         password: "123456",
         *         }
         * 
         *         Use this user to run the first requisition on API and create new 
         *         users
         */

        List<User> lUsers = userRepository.findByEmail("admin@base.com");

        if (lUsers.isEmpty()) {
            User user = new User();
            user.setName("Admin");
            user.setEmail("admin@base.com");
            user.setRole("base");
            user.setPassword(passwordEncoder.encode("123456"));

            userRepository.save(user);
        }

    }

    // Get all users
    @GetMapping
    public ResponseEntity<JsonResponse> listProducts() {
        List<User> lProducts = userRepository.findAll();
        return new ResponseEntity<>(new JsonResponse("Ok", 200, lProducts), null, 200);
    }

    // Get user by id
    @GetMapping("{id}")
    public ResponseEntity<JsonResponse> getProduct(@PathVariable long id) {
        Optional<User> user = userRepository.findById(id);

        if (!user.isPresent())
            return new ResponseEntity<>(new JsonResponse("User not found", 404, user), null, 404);

        return new ResponseEntity<>(new JsonResponse("ok", 200, user), null, 200);
    }

    // Create user
    @PostMapping
    public ResponseEntity<JsonResponse> post(@RequestBody UserDTO user) {
        try {
            User persistUser = new User();

            persistUser.setEmail(user.getEmail());
            persistUser.setName(user.getName());
            persistUser.setPassword(passwordEncoder.encode(user.getPassword()));
            persistUser.setRole(user.getRole());

            userRepository.save(persistUser);

            return new ResponseEntity<>(new JsonResponse("User created successfully", 200, persistUser), null, 200);
        } catch (Exception e) {
            return new ResponseEntity<>(new JsonResponse(e.getMessage(), 500, null), null, 500);
        }
    }

    // Update user
    @PutMapping("{id}")
    public ResponseEntity<JsonResponse> update(@PathVariable long id, @RequestBody UserDTO user) {
        try {
            User persistUser = new User();

            persistUser.setEmail(user.getEmail());
            persistUser.setName(user.getName());
            persistUser.setPassword(passwordEncoder.encode(user.getPassword()));
            persistUser.setRole(user.getRole());
            persistUser.setId(id);

            userRepository.save(persistUser);

            return new ResponseEntity<>(new JsonResponse("ok", 200, persistUser), null, 200);

        } catch (Exception e) {
            return new ResponseEntity<>(new JsonResponse(e.getMessage(), 500, null), null, 500);
        }
    }

    // Delete user
    @DeleteMapping("{id}")
    public ResponseEntity<JsonResponse> destroy(@PathVariable long id) {
        try {
            userRepository.deleteById(id);
            return new ResponseEntity<>(new JsonResponse("User deleted successfully", 200, null), null, 200);
        } catch (Exception e) {
            return new ResponseEntity<>(new JsonResponse(e.getMessage(), 500, null), null, 500);
        }
    }
}
