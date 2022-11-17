package com.gbrramos.lix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gbrramos.lix.models.Product;

public interface IProductRepository extends JpaRepository<Product, Long> {
    
}
