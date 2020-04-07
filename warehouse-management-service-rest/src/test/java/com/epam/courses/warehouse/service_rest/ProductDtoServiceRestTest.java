package com.epam.courses.warehouse.service_rest;

import com.epam.courses.warehouse.model.dto.ProductDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(value = {"classpath:app-context-test.xml"})
class ProductDtoServiceRestTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductDtoServiceRestTest.class);

    private String URL = "http://localhost:8088/products_dtos";

    @Autowired
    private RestTemplate restTemplate;

    private MockRestServiceServer mockServer;

    private ObjectMapper mapper = new ObjectMapper();

    private ProductDtoServiceRest productDtoService;

    @BeforeEach
    public void before() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
        productDtoService = new ProductDtoServiceRest(URL, restTemplate);
    }

    @Test
    public void shouldGetAllProductsWithSummaryCount() throws Exception {
        LOGGER.debug("ProductDtoServiceRestTest:dhouldGetAllProductsWithSummaryCount");

        mockServer.expect(ExpectedCount.once(), requestTo(new URI(URL)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(Arrays.asList(create(0), create(1)))));

        List<ProductDto> products = productDtoService.getAllProductsWithSummaryCount();

        assertNotNull(products);
        assertEquals(2, products.size());
    }

    private ProductDto create(int index) {
        return new ProductDto()
                .setProductId(index)
                .setProductName("Product " + index)
                .setProductSumCount(index + 1);
    }
}