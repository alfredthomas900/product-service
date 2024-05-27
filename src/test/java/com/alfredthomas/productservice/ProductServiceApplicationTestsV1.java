package com.alfredthomas.productservice;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MongoDBContainer;

/**
 * Integration test for the ProductServiceApplicationV1. Ensure to have the Docker Docker up and
 * running before running the tests Or else, the tests will fail
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductServiceApplicationTestsV1 {

  @ServiceConnection static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.5");

  @LocalServerPort private Integer port;

  @BeforeEach
  void setUp() {
    RestAssured.baseURI = "http://localhost";
    RestAssured.port = port;
  }

  static {
    mongoDBContainer.start();
  }

  @Test
  void shouldCreateProduct() {
    String requestBody =
        """
          {
               "name": "Samsung S24 Ultra",
               "description": "Samsung S24 Ultra is a smartphone from Samsung",
               "price": 1600
           }
          """;
    RestAssured.given()
        .contentType("application/json")
        .body(requestBody)
        .when()
        .post("/api/productv1")
        .then()
        .statusCode(201)
        .body("id", Matchers.notNullValue())
        .body("name", Matchers.equalTo("Samsung S24 Ultra"))
        .body("description", Matchers.equalTo("Samsung S24 Ultra is a smartphone from Samsung"))
        .body("price", Matchers.equalTo(1600));
  }
}
