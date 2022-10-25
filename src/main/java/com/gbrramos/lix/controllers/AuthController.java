package com.gbrramos.lix.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.gbrramos.lix.models.ExceptionResponse;
import com.gbrramos.lix.models.JwtRequest;
import com.gbrramos.lix.models.JwtResponse;
import com.gbrramos.lix.services.JwtUserDetailService;
import com.gbrramos.lix.utils.TokenService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@CrossOrigin
public class AuthController {

    @Autowired
    private JwtUserDetailService userDetailService;

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private TokenService tokenService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody JwtRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (DisabledException e) {
            return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.getMessage(), 500), null, 500);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.getMessage(), 500), null, 500);
        }

        final UserDetails userDetails = userDetailService.loadUserByUsername(request.getUsername());
        final String jwtToken = tokenService.generateJwtToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(jwtToken));

    }
    
}
