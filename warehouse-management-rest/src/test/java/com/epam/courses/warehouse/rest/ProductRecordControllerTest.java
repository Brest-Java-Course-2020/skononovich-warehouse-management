package com.epam.courses.warehouse.rest;

import com.epam.courses.warehouse.model.Product;
import com.epam.courses.warehouse.model.ProductRecord;
import com.epam.courses.warehouse.service.ProductRecordService;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductRecordControllerTest {

    @InjectMocks
    private ProductRecordController productRecordController;

    @Mock
    private ProductRecordService productRecordService;

    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void before(){
        mockMvc = MockMvcBuilders.standaloneSetup(productRecordController).build();
    }

    @AfterEach
    public void after(){
        verifyNoMoreInteractions(productRecordService);
    }

    @Test
    void shouldCreateProductRecord() throws Exception {
        when(productRecordService.create(any(ProductRecord.class))).thenReturn(0);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/records")
                        .content(objectMapper.writeValueAsString(new Product()))
                        .contentType("application/json")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("0"))
        ;

        verify(productRecordService).create(any(ProductRecord.class));
    }
}