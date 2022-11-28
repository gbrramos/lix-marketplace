package com.gbrramos.lix.controllers;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gbrramos.lix.models.JsonResponse;
import com.gbrramos.lix.models.Product;
import com.gbrramos.lix.repositories.IProductRepository;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private IProductRepository productRepository;

    @GetMapping
    public ResponseEntity<JsonResponse> listProducts() {
        List<Product> lProducts = productRepository.findAll();
        return new ResponseEntity<JsonResponse>(new JsonResponse("Ok", 200, lProducts), null, 200);
    }

    @GetMapping("{id}")
    public ResponseEntity<JsonResponse> getProduct(@PathVariable long id) throws Exception {
        Optional<Product> product = productRepository.findById(id);

        if(!product.isPresent()) 
            return new ResponseEntity<JsonResponse> (new JsonResponse("Product not found", 404, product), null, 404);

        return new ResponseEntity<JsonResponse>(new JsonResponse("ok", 200, product), null, 200);
    }

    @PostMapping
    public ResponseEntity<JsonResponse> post(@RequestBody Product rProduct) throws Exception  {
        try {
            productRepository.save(rProduct);
        } catch (Exception e) {
            return new ResponseEntity<JsonResponse>(new JsonResponse(e.getMessage(), 500, null), null, 500);
        }
        return new ResponseEntity<JsonResponse>(new JsonResponse("ok", 200, rProduct), null, 200);
    }

    @PutMapping("{id}")
    public ResponseEntity<JsonResponse> update (@PathVariable long id, @RequestBody Product rProduct) {
        try {
            rProduct.setId(id);
            productRepository.save(rProduct);
        } catch (Exception e) {
            return new ResponseEntity<JsonResponse>(new JsonResponse(e.getMessage(), 500, null), null, 500);
        }
        return new ResponseEntity<JsonResponse>(new JsonResponse("ok", 200, rProduct), null, 200);
    } 

    @DeleteMapping("{id}")
    public ResponseEntity<JsonResponse> destroy (@PathVariable long id) {
        try {
            productRepository.deleteById(id);
        } catch (Exception e) {
            return new ResponseEntity<JsonResponse>(new JsonResponse(e.getMessage(), 500, null), null, 500);
        }
        return new ResponseEntity<JsonResponse>(new JsonResponse("Product deleted successfully", 200, null), null, 200);
    }
    
}
