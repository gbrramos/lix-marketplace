package com.gbrramos.lix.controllers;

import java.util.List;
import java.util.Optional;

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
import com.gbrramos.lix.models.ProductDTO;
import com.gbrramos.lix.models.Product;
import com.gbrramos.lix.repositories.IProductRepository;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private IProductRepository productRepository;

    @GetMapping
    public ResponseEntity<JsonResponse> listProducts() {
        try {
            List<Product> lProducts = productRepository.findAll();
            return new ResponseEntity<>(new JsonResponse("Ok", 200, lProducts), null, 200);
        } catch (Exception e) {
            return new ResponseEntity<>(new JsonResponse(e.getMessage(), 404, null), null, 404);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<JsonResponse> getProduct(@PathVariable long id) {
        try {
            Optional<Product> product = productRepository.findById(id);
            return new ResponseEntity<>(new JsonResponse("ok", 200, product), null, 200);
        } catch (Exception e) {
            return new ResponseEntity<>(new JsonResponse(e.getMessage(), 404, null), null, 404);
        }
    }

    @PostMapping
    public ResponseEntity<JsonResponse> post(@RequestBody ProductDTO product) {
        try {
            Product persistProduct = new Product();

            persistProduct.setCode(product.getCode());
            persistProduct.setDescription(product.getDescription());
            persistProduct.setName(product.getName());
            persistProduct.setPrice(product.getPrice());
            persistProduct.setSpecialPrice(product.getSpecialPrice());

            productRepository.save(persistProduct);

            return new ResponseEntity<>(new JsonResponse("Product not found", 404, product), null, 404);
        } catch (Exception e) {
            return new ResponseEntity<>(new JsonResponse("Product not found", 404, product), null, 404);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<JsonResponse> update(@PathVariable long id, @RequestBody ProductDTO product) {
        try {
            Product persistProduct = new Product();

            persistProduct.setCode(product.getCode());
            persistProduct.setDescription(product.getDescription());
            persistProduct.setName(product.getName());
            persistProduct.setPrice(product.getPrice());
            persistProduct.setSpecialPrice(product.getSpecialPrice());
            persistProduct.setId(id);

            productRepository.save(persistProduct);

            return new ResponseEntity<>(new JsonResponse("ok", 200, persistProduct), null, 200);
        } catch (Exception e) {
            return new ResponseEntity<>(new JsonResponse(e.getMessage(), 500, null), null, 500);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<JsonResponse> destroy(@PathVariable long id) {
        try {
            productRepository.deleteById(id);
            return new ResponseEntity<>(new JsonResponse("Product deleted successfully", 200, null), null, 200);
        } catch (Exception e) {
            return new ResponseEntity<>(new JsonResponse(e.getMessage(), 500, null), null, 500);
        }
    }

}
