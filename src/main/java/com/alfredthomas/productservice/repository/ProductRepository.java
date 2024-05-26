package com.alfredthomas.productservice.repository;

import com.alfredthomas.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {}
