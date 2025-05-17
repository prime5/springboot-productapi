package com.example.productapi.controller;

import com.example.productapi.model.Product;
import com.example.productapi.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductRepository productRepository;

    @Test
    void shouldReturnListOfProducts() throws Exception {
        Product p1 = new Product("iPhone", "Flagship phone", 1299.0);
        Product p2 = new Product("Pixel", "Android phone", 999.0);

        when(productRepository.findAll()).thenReturn(List.of(p1, p2));

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].name").value("iPhone"))
                .andExpect(jsonPath("$[1].name").value("Pixel"));
    }
    @Test
    void shouldReturnProductById() throws Exception {
        Product p1 = new Product("MacBook", "Apple laptop", 1999.0);
        p1.setId("abc123");

        when(productRepository.findById("abc123")).thenReturn(Optional.of(p1));

        mockMvc.perform(get("/api/products/abc123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("MacBook"));
    }
    @Test
    void shouldCreateProduct() throws Exception {
        Product newProduct = new Product("iPad", "Tablet", 799.0);

        when(productRepository.save(any(Product.class))).thenReturn(newProduct);

        mockMvc.perform(post("/api/products")
                        .contentType("application/json")
                        .content("""
                {
                  "name": "iPad",
                  "description": "Tablet",
                  "price": 799.0
                }
            """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("iPad"))
                .andExpect(jsonPath("$.price").value(799.0));
    }
    @Test
    void shouldUpdateProduct() throws Exception {
        Product existing = new Product("iPad", "Tablet", 799.0);
        existing.setId("p1");

        Product updated = new Product("iPad Pro", "Tablet Pro", 999.0);
        updated.setId("p1");

        when(productRepository.findById("p1")).thenReturn(Optional.of(existing));
        when(productRepository.save(any(Product.class))).thenReturn(updated);

        mockMvc.perform(put("/api/products/p1")
                        .contentType("application/json")
                        .content("""
                {
                  "name": "iPad Pro",
                  "description": "Tablet Pro",
                  "price": 999.0
                }
            """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("iPad Pro"))
                .andExpect(jsonPath("$.price").value(999.0));
    }
    @Test
    void shouldDeleteProduct() throws Exception {
        when(productRepository.existsById("p1")).thenReturn(true);

        mockMvc.perform(delete("/api/products/p1"))
                .andExpect(status().isNoContent());
    }


}
