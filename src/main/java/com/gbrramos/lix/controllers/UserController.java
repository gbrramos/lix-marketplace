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
import com.gbrramos.lix.repositories.IUserRepository;

@Controller
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IUserRepository userRepository;

    // @Autowired
    // TestService testService;

    // @Autowired
    // UserService userService;

    // @Autowired
    // private JwtTokenUtil jwtTokenUtil;

    // @RequestMapping(value="/test", method = RequestMethod.GET, produces = "application/json")
    // @ResponseBody
    // public List<Test> getTestsListByUserId(HttpServletRequest req){
    //     String token = req.getHeader(HEADER_STRING).replace(TOKEN_PREFIX,"");
    //     return testService.findByUserId(userService.findByUsername(jwtTokenUtil.getUsernameFromToken(token)));
    // }

    // Create the first user when API is executed
    @Bean
    private void createBaseUser() {
        
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

    // Get all users
    @GetMapping
    public ResponseEntity<JsonResponse> listProducts() {
        List<User> lProducts = userRepository.findAll();
        return new ResponseEntity<JsonResponse>(new JsonResponse("Ok", 200, lProducts), null, 200);
    }

    @GetMapping("{id}")
    public ResponseEntity<JsonResponse> getProduct(@PathVariable long id) throws Exception {
        Optional<User> user = userRepository.findById(id);

        if(!user.isPresent()) 
            return new ResponseEntity<JsonResponse> (new JsonResponse("User not found", 404, user), null, 404);

        return new ResponseEntity<JsonResponse>(new JsonResponse("ok", 200, user), null, 200);
    }

    @PostMapping
    public ResponseEntity<JsonResponse> post(@RequestBody User rUser) throws Exception  {
        try {
            userRepository.save(rUser);
        } catch (Exception e) {
            return new ResponseEntity<JsonResponse>(new JsonResponse(e.getMessage(), 500, null), null, 500);
        }
        return new ResponseEntity<JsonResponse>(new JsonResponse("User created successfully", 200, rUser), null, 200);
    }

    @PutMapping("{id}")
    public ResponseEntity<JsonResponse> update (@PathVariable long id, @RequestBody User rUser) {
        try {
            rUser.setId(id);
            userRepository.save(rUser);
        } catch (Exception e) {
            return new ResponseEntity<JsonResponse>(new JsonResponse(e.getMessage(), 500, null), null, 500);
        }
        return new ResponseEntity<JsonResponse>(new JsonResponse("ok", 200, rUser), null, 200);
    } 

    @DeleteMapping("{id}")
    public ResponseEntity<JsonResponse> destroy (@PathVariable long id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            return new ResponseEntity<JsonResponse>(new JsonResponse(e.getMessage(), 500, null), null, 500);
        }
        return new ResponseEntity<JsonResponse>(new JsonResponse("User deleted successfully", 200, null), null, 200);
    }
}
