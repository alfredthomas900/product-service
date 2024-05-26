package com.alfredthomas.productservice.service;

import com.alfredthomas.productservice.dto.ProductRequestRecord;
import com.alfredthomas.productservice.dto.ProductResponse;
import com.alfredthomas.productservice.dto.ProductResponseRecord;
import com.alfredthomas.productservice.model.Product;
import com.alfredthomas.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceV1 {

  private final ProductRepository productRepository;

  public ProductResponseRecord createProduct(ProductRequestRecord productRequestRecord) {
    Product product =
        Product.builder()
            .name(productRequestRecord.name())
            .description(productRequestRecord.description())
            .price(productRequestRecord.price())
            .build();
    productRepository.save(product);
    log.debug("createProduct :: Product : [{}] is saved", product.getId());
    return new ProductResponseRecord(
        product.getId(), product.getName(), product.getDescription(), product.getPrice());
  }

  public List<ProductResponseRecord> getAllProducts() {
    log.debug("getAllProducts :: started");
    return productRepository.findAll().stream()
        .map(
            product ->
                new ProductResponseRecord(
                    product.getId(),
                    product.getName(),
                    product.getDescription(),
                    product.getPrice()))
        .collect(Collectors.toList());
  }
}
