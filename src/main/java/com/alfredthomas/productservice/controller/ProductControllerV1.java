package com.alfredthomas.productservice.controller;

import com.alfredthomas.productservice.dto.ProductRequestRecord;
import com.alfredthomas.productservice.dto.ProductResponseRecord;
import com.alfredthomas.productservice.model.Product;
import com.alfredthomas.productservice.service.ProductServiceV1;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/productv1")
@RequiredArgsConstructor
@Slf4j
public class ProductControllerV1 {

  private final ProductServiceV1 productServiceV1;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ProductResponseRecord createProduct(
      @RequestBody ProductRequestRecord productRequestRecord) {
    log.debug("createProduct :: started for request : {}", productRequestRecord);
    return productServiceV1.createProduct(productRequestRecord);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<ProductResponseRecord> getAllProducts() {
    log.debug("getAllProducts :: started");
    return productServiceV1.getAllProducts();
  }
}
