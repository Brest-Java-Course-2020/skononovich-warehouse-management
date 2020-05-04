package com.epam.courses.warehouse.web_app;

import com.epam.courses.warehouse.model.DealTypes;
import com.epam.courses.warehouse.model.ProductRecord;
import com.epam.courses.warehouse.model.dto.ProductRecordDTO;
import com.epam.courses.warehouse.service_rest.ProductRecordDtoServiceRest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Date;
import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:app-context-test.xml"})
class RecordControllerTest {

    @Autowired
    private ProductRecordDtoServiceRest productRecordDtoServiceRest;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeEach
    public void before(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    void shouldGoToProductsCirculationPage() throws Exception {
        when(productRecordDtoServiceRest.getAll()).thenReturn(Arrays.asList(createRecordDto(1), createRecordDto(2)));

        mockMvc.perform(
                MockMvcRequestBuilders.get("/circulation")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("circulation"))
                .andExpect(model().attribute("records", hasItem(
                        allOf(
                                hasProperty("recordId", is(1)),
                                hasProperty("productName", is("Product 1")),
                                hasProperty("quantityOfProduct", is(1)),
                                hasProperty("dealType", is(DealTypes.DELIVERY))
                        )
                )))
                .andExpect(model().attribute("records", hasItem(
                        allOf(
                                hasProperty("recordId", is(2)),
                                hasProperty("productName", is("Product 2")),
                                hasProperty("quantityOfProduct", is(2)),
                                hasProperty("dealType", is(DealTypes.GETTING))
                        )
                )))
        ;
    }

    @Test
    void shouldGetFilterRecords() throws Exception {
        Date date = Date.valueOf("2011-11-11");
        when(productRecordDtoServiceRest.getAllInTimeInterval(date, date))
                .thenReturn(Arrays.asList(createRecordDto(1), createRecordDto(2)));

        mockMvc.perform(
                MockMvcRequestBuilders.get("/filter")
                .param("startInterval", date.toString())
                .param("endInterval", date.toString())
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("circulation"))
                .andExpect(model().attribute("records", hasItem(
                        allOf(
                                hasProperty("recordId", is(1)),
                                hasProperty("productName", is("Product 1")),
                                hasProperty("quantityOfProduct", is(1)),
                                hasProperty("dealType", is(DealTypes.DELIVERY))
                        )
                )))
                .andExpect(model().attribute("records", hasItem(
                        allOf(
                                hasProperty("recordId", is(2)),
                                hasProperty("productName", is("Product 2")),
                                hasProperty("quantityOfProduct", is(2)),
                                hasProperty("dealType", is(DealTypes.GETTING))
                        )
                )))
        ;
    }

    @Test
    void getRecordPage() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/record")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("record"))
                .andExpect(model().attributeExists("products", "dealTypes", "productRecord"))
        ;
    }

    @Test
    void shouldCreateRecord() throws Exception {
        ProductRecord productRecord = new ProductRecord()
                .setProductRecordId(1)
                .setDealType(DealTypes.GETTING)
                .setQuantityOfProduct(1)
                .setProductId(1)
                .setProductRecordDate(Date.valueOf("2020-01-01"));

        mockMvc.perform(
                MockMvcRequestBuilders.post("/record")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("productRecordId", String.valueOf(productRecord.getProductRecordId()))
                        .param("dealType", String.valueOf(productRecord.getDealType()))
                        .param("quantityOfProduct", String.valueOf(productRecord.getQuantityOfProduct()))
                        .param("productId", String.valueOf(productRecord.getProductId()))
                        .param("productRecordDate", String.valueOf(productRecord.getProductRecordDate()))
                        .sessionAttr("productRecord", productRecord)
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("record"))
                .andExpect(model().attributeExists("products", "dealTypes", "productRecord"))
        ;
    }

    private ProductRecordDTO createRecordDto(int i) {
        return new ProductRecordDTO().setRecordId(i)
                .setProductName("Product " + i)
                .setQuantityOfProduct(i)
                .setDealType(DealTypes.fromInt(i & 1));
    }
}