package com.gbrramos.lix.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController     
public class CarsController {

    @GetMapping("/")
    public String teste(){
        return "Hello World";
    }

}
