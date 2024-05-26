package com.alfredthomas.productservice.dto;

import java.math.BigDecimal;

public record ProductRequestRecord(String id, String name, String description, BigDecimal price) {}
