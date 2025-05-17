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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

}
