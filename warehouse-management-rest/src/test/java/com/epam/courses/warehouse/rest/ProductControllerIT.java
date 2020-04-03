package com.epam.courses.warehouse.rest;

import com.epam.courses.warehouse.model.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:app-context-test.xml"})
@Rollback
@Transactional
public class ProductControllerIT {

    public static final String TEST_PRODUCT_NAME = "testProduct";
    public static final String PRODUCTS_ENDPOINT = "/products";

    @Autowired
    private ProductController productController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void before(){
        mockMvc = MockMvcBuilders.standaloneSetup(productController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();
    }

    @Test
    public void shouldGetAllProducts() throws Exception {
        getAllProducts();
    }

    @Test
    public void shouldCreateAndGetProductById() throws Exception {

        Integer productId = createProduct(TEST_PRODUCT_NAME);
        Product product = getProductById(productId);

        assertEquals(TEST_PRODUCT_NAME, product.getProductName());
    }

    @Test
    public void shouldUpdateProduct() throws Exception {

        Integer productId = createProduct(TEST_PRODUCT_NAME);

        Product product = new Product()
                .setProductId(productId)
                .setProductName("newName");

        MockHttpServletResponse response = mockMvc.perform(
                MockMvcRequestBuilders.put(PRODUCTS_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product))
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertNotNull(response);

        int numberOfUpdatedRecords = objectMapper.readValue(response.getContentAsString(), Integer.class);

        assertEquals(1, numberOfUpdatedRecords);

        Product updatedProduct = getProductById(productId);

        assertNotNull(updatedProduct);
        assertEquals(product.getProductName(), updatedProduct.getProductName());
    }

    @Test
    public void shouldDeleteProduct() throws Exception {
        Integer productId = createProduct(TEST_PRODUCT_NAME);

        int sizeBeforeDelete = getAllProducts().size();

        MockHttpServletResponse response = mockMvc.perform(
                MockMvcRequestBuilders.delete(PRODUCTS_ENDPOINT + "/" + productId)
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        int numberOfDeletedRecords = objectMapper.readValue(response.getContentAsString(), Integer.class);
        assertEquals(1, numberOfDeletedRecords);

        int sizeAfterDelete = getAllProducts().size();

        assertEquals(sizeBeforeDelete, sizeAfterDelete + 1);
    }

    private List<Product> getAllProducts() throws Exception {

        MockHttpServletResponse response = mockMvc.perform(
                MockMvcRequestBuilders.get(PRODUCTS_ENDPOINT)
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        List<Product> productList = objectMapper.readValue(response.getContentAsString(),
                new TypeReference<List<Product>>(){});

        assertNotNull(productList);
        assertTrue(productList.size() > 0);

        return productList;
    }

    private Integer createProduct(String productName) throws Exception {

        MockHttpServletResponse response = mockMvc.perform(
                MockMvcRequestBuilders.post(PRODUCTS_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productName))
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        Integer productId = objectMapper.readValue(response.getContentAsString(), Integer.class);
        assertNotNull(productId);

        return productId;
    }

    private Product getProductById(Integer productId) throws Exception {

        MockHttpServletResponse response = mockMvc.perform(
                MockMvcRequestBuilders.get(PRODUCTS_ENDPOINT + "/" + productId)
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        Product responseProduct = objectMapper.readValue(response.getContentAsString(), Product.class);

        assertNotNull(responseProduct);

        return responseProduct;
    }
}
