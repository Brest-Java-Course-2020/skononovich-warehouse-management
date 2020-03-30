package com.epam.courses.warehouse.rest;

import com.epam.courses.warehouse.model.Product;
import com.epam.courses.warehouse.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void before(){
        mockMvc = MockMvcBuilders.standaloneSetup(productController)
                .build();
    }

    @AfterEach
    public void after(){
        verifyNoMoreInteractions(productService);
    }

    @Test
    public void shouldGetAllProducts() throws Exception {
        when(productService.getAll()).thenReturn(Arrays.asList(create(0), create(1)));

        mockMvc.perform(
                MockMvcRequestBuilders.get("/products")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].productId", Matchers.is(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].productName", Matchers.is("product 0")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].productId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].productName", Matchers.is("product 1")))
        ;

        verify(productService).getAll();
    }

    @Test
    public void shouldFindById() throws Exception {
        when(productService.getById(2)).thenReturn(Optional.of(create(2)));

        mockMvc.perform(
                MockMvcRequestBuilders.get("/products/2")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("productId", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("productName", Matchers.is("product 2")))
        ;

        verify(productService).getById(2);
    }

    @Test
    public void shouldCreateProduct() throws Exception {
        when(productService.create(any(Product.class))).thenReturn(6);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/products")
                        .content("test")
                        .contentType("application/json")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("6"))
        ;

        verify(productService).create(any(Product.class));
    }

    @Test
    public void shouldUpdateProduct() throws Exception {
        when(productService.update(any(Product.class))).thenReturn(1);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/products")
                        .content(objectMapper.writeValueAsString(new Product()))
                        .contentType("application/json")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("1"))
        ;

        verify(productService).update(any(Product.class));
    }

    @Test
    public void shouldDeleteProduct() throws Exception {
        when(productService.delete(5)).thenReturn(1);

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/products/5")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("1"))
        ;

        verify(productService).delete(5);
    }


    private Product create(Integer index) {
        return new Product()
                .setProductId(index)
                .setProductName("product " + index);
    }
}
