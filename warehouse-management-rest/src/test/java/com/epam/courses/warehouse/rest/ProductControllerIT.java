package com.epam.courses.warehouse.rest;

import com.epam.courses.warehouse.model.Product;
import com.epam.courses.warehouse.rest.exception.CustomExceptionHandler;
import com.epam.courses.warehouse.rest.exception.ErrorResponse;
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

import static com.epam.courses.warehouse.rest.exception.CustomExceptionHandler.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:app-context-test.xml"})
@Rollback
@Transactional
public class ProductControllerIT {

    public static final String TEST_PRODUCT_NAME = "testProduct";
    public static final String PRODUCTS_ENDPOINT = "/products";

    @Autowired
    private ProductController productController;

    @Autowired
    private CustomExceptionHandler customExceptionHandler;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void before(){
        mockMvc = MockMvcBuilders.standaloneSetup(productController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .setControllerAdvice(customExceptionHandler)
                .build();
    }

    @Test
    public void shouldGetAllProducts() throws Exception {
        getAllProducts();
    }

    @Test
    public void shouldCreateAndGetProductById() throws Exception {

        Integer productId = createProduct(new Product().setProductName(TEST_PRODUCT_NAME));
        Product product = getProductById(productId);

        assertEquals(TEST_PRODUCT_NAME, product.getProductName());
    }

    @Test
    public void shouldUpdateProduct() throws Exception {

        Integer productId = createProduct(new Product().setProductName(TEST_PRODUCT_NAME));

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
        Integer productId = createProduct(new Product().setProductName(TEST_PRODUCT_NAME));

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

    @Test
    public void shouldReturnProductNotFoundException() throws Exception {
        MockHttpServletResponse response =
                mockMvc.perform(MockMvcRequestBuilders.get("/products/999999")
                        .accept(MediaType.APPLICATION_JSON)
                ).andExpect(status().isNotFound())
                        .andReturn().getResponse();
        assertNotNull(response);
        ErrorResponse errorResponse = objectMapper.readValue(response.getContentAsString(), ErrorResponse.class);
        assertNotNull(errorResponse);
        assertEquals(errorResponse.getMessage(), PRODUCT_NOT_FOUND);
    }

    @Test
    public void shouldReturnProductIsExistException() throws Exception {
        Product product = new Product().setProductName("TEST");
        Product conflictProduct = new Product().setProductName(product.getProductName());

        createProduct(product);

        MockHttpServletResponse response = mockMvc.perform(
                MockMvcRequestBuilders.post(PRODUCTS_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(conflictProduct))
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertNotNull(response);
        ErrorResponse errorResponse = objectMapper.readValue(response.getContentAsString(), ErrorResponse.class);
        assertNotNull(errorResponse);
        assertEquals(errorResponse.getMessage(), PRODUCT_IS_EXIST);
    }

    @Test
    public void shouldFailOnDeleteProductWithRecords() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(
                MockMvcRequestBuilders.delete(PRODUCTS_ENDPOINT + "/1")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertNotNull(response);
        ErrorResponse errorResponse = objectMapper.readValue(response.getContentAsString(), ErrorResponse.class);
        assertNotNull(errorResponse);
        assertEquals(errorResponse.getMessage(), VALIDATION_ERROR);
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

    private Integer createProduct(Product product) throws Exception {

        MockHttpServletResponse response = mockMvc.perform(
                MockMvcRequestBuilders.post(PRODUCTS_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product))
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
