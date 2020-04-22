package com.epam.courses.warehouse.rest;

import com.epam.courses.warehouse.model.DealTypes;
import com.epam.courses.warehouse.model.ProductRecord;
import com.epam.courses.warehouse.rest.exception.CustomExceptionHandler;
import com.epam.courses.warehouse.rest.exception.ErrorResponse;
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

import java.sql.Date;

import static com.epam.courses.warehouse.rest.exception.CustomExceptionHandler.PRODUCT_NOT_ENOUGH;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:app-context-test.xml")
@Transactional
@Rollback
class ProductRecordControllerIT {

    public static final String RECORDS_ENDPOINT = "/records";
    @Autowired
    private ProductRecordController productRecordController;

    @Autowired
    private CustomExceptionHandler customExceptionHandler;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void before(){
        mockMvc = MockMvcBuilders.standaloneSetup(productRecordController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .setControllerAdvice(customExceptionHandler)
                .build();
    }

    @Test
    void shouldCreateRecord() throws Exception {

        ProductRecord productRecord = new ProductRecord()
                .setProductId(2)
                .setDealType(DealTypes.GETTING)
                .setProductRecordDate(Date.valueOf("2020-12-11"))
                .setQuantityOfProduct(5);

        MockHttpServletResponse response = mockMvc.perform(
                MockMvcRequestBuilders.post(RECORDS_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productRecord))
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        Integer recordId = objectMapper.readValue(response.getContentAsString(), Integer.class);

        assertNotNull(recordId);
    }

    @Test
    void shouldReturnProductNotEnoughException() throws Exception {
        ProductRecord productRecord = new ProductRecord()
                .setProductId(3)
                .setDealType(DealTypes.DELIVERY)
                .setProductRecordDate(Date.valueOf("2020-12-11"))
                .setQuantityOfProduct(5);

        MockHttpServletResponse response = mockMvc.perform(
                MockMvcRequestBuilders.post(RECORDS_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productRecord))
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertNotNull(response);
        ErrorResponse errorResponse = objectMapper.readValue(response.getContentAsString(), ErrorResponse.class);
        assertNotNull(errorResponse);
        assertEquals(errorResponse.getMessage(), PRODUCT_NOT_ENOUGH);
    }
}