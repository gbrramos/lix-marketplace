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
    public ResponseEntity<List<User>> listUsers() {
        List<User> lUsers = userRepository.findAll();
        return new ResponseEntity<List<User>>(lUsers, null, 200);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<User>> getById(@PathVariable long id) throws Exception {
        Optional<User> user = userRepository.findById(id);

        if(!user.isPresent()) 
            throw new Exception("User not found");
        
        return new ResponseEntity<Optional<User>>(user, null, 200);
    }

    @PostMapping
    public ResponseEntity<User> post(@RequestBody User regUser) throws Exception {
        User user = new User();

        try {

            user.setEmail(regUser.getEmail());
            user.setName(regUser.getName());
            user.setPassword(passwordEncoder.encode(regUser.getPassword()));
            user.setCompany_id(1);
            user.setRole(regUser.getRole());
    
            userRepository.save(user);
    
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
       
        return new ResponseEntity<User>(user, null, 200);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> update(@RequestBody User regUser, @PathVariable long id) throws Exception {
        User user = new User();

        try {
            user.setId(id);
            user.setEmail(regUser.getEmail());
            user.setName(regUser.getName());
            user.setPassword(passwordEncoder.encode(regUser.getPassword()));
            user.setCompany_id(1);
            user.setRole(regUser.getRole());
    
            userRepository.save(user);
    
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
       
        return new ResponseEntity<User>(user, null, 200);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        userRepository.deleteById(id);

        return new ResponseEntity<String>("ok", null, 200);
    }
}
