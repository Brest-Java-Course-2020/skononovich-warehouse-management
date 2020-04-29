package com.epam.courses.warehouse.service_rest;

import com.epam.courses.warehouse.model.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(value = {"classpath:app-context-test.xml"})
class ProductServiceRestTest {
    private int PRODUCT_ID = 6;

    private String URL = "http://localhost:8088/products";

    @Autowired
    private RestTemplate restTemplate;

    private MockRestServiceServer mockServer;

    private ObjectMapper mapper = new ObjectMapper();

    private ProductServiceRest productServiceRest;

    @BeforeEach
    public void before() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
        productServiceRest = new ProductServiceRest(URL, restTemplate);
    }

    @Test
    void shouldCreateProduct() throws Exception {
        mockServer.expect(ExpectedCount.once(), requestTo(new URI(URL)))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(PRODUCT_ID)));

        int productId = productServiceRest.create(new Product().setProductName("Product"));

        assertEquals(PRODUCT_ID, productId);
    }

    @Test
    void shouldGetAllProducts() throws Exception {
        mockServer.expect(ExpectedCount.once(), requestTo(new URI(URL)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(Arrays.asList(create(0), create(1)))));

        List<Product> products = productServiceRest.getAll();

        assertNotNull(products);
        assertEquals(2, products.size());
    }

    @Test
    void shouldGetProductById() throws Exception {
        Integer id = 1;
        Product product = create(id);

        mockServer.expect(ExpectedCount.once(), requestTo(new URI(URL + "/" + id)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(product))
                );

        Optional<Product> optionalProduct = productServiceRest.getById(id);

        assertTrue(optionalProduct.isPresent());
        assertEquals(id, optionalProduct.get().getProductId());
        assertEquals(product.getProductName(), optionalProduct.get().getProductName());
    }

    @Test
    void shouldUpdateProduct() throws Exception {
        int id = 1;
        Product product = create(id);

        mockServer.expect(ExpectedCount.once(), requestTo(new URI(URL)))
                .andExpect(method(HttpMethod.PUT))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString("1"))
                );

        int result = productServiceRest.update(product);

        assertEquals(1, result);
    }

    @Test
    void shouldDeleteProduct() throws Exception {
        Integer id = 1;
        mockServer.expect(ExpectedCount.once(), requestTo(new URI(URL + "/" + id)))
                .andExpect(method(HttpMethod.DELETE))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString("1"))
                );

        int result = productServiceRest.delete(id);

        assertEquals(1, result);
    }

    @Test
    public void shouldGetTrueIfProductExist() throws URISyntaxException, JsonProcessingException {
        Product product = create(5);
        mockServer.expect(ExpectedCount.once(), requestTo(new URI(URL)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(Collections.singletonList(product)))
                );

        boolean response = productServiceRest.productExist(product);
        assertTrue(response);
    }

    @Test
    public void shouldGetFalseIfProductNotExist() throws URISyntaxException, JsonProcessingException {
        Product product = create(5);
        mockServer.expect(ExpectedCount.once(), requestTo(new URI(URL)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(Collections.singletonList(product)))
                );
        Product nonExistProduct = new Product().setProductName("Non exist product name");

        boolean response = productServiceRest.productExist(nonExistProduct);
        assertFalse(response);
    }

    private Product create(int index) {
        return new Product()
                .setProductId(index)
                .setProductName("Product " + index);
    }
}