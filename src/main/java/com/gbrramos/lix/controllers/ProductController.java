package com.gbrramos.lix.controllers;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gbrramos.lix.models.Product;
import com.gbrramos.lix.repositories.IProductRepository;


@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private IProductRepository productRepository;

    @GetMapping
    public ResponseEntity<List<Product>> listProducts() {
        List<Product> lProducts = productRepository.findAll();
        return new ResponseEntity<List<Product>>(lProducts, null, 200);
    }

    @GetMapping("{param}")
    public ResponseEntity<Optional<Product>> getProduct(@PathVariable long param) throws Exception {
        // Discover a way to make this req return data passing id or code as a param
        Optional<Product> product = productRepository.findById(param);

        if(!product.isPresent()) 
            throw new Exception("Product not found");

        return new ResponseEntity<Optional<Product>>(product, null, 200);
    }
    
}
