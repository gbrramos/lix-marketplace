package com.gbrramos.lix.controllers;

import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController     
public class CarsController {

    @Bean
    @GetMapping("/")
    public String teste(){
        return "Hello World";
    }

}
