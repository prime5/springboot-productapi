package com.example.productapi.repository;

import com.example.productapi.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
    // Additional query methods can be added here later
}
