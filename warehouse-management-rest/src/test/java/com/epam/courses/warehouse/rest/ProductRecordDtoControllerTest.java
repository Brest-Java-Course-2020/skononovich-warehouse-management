package com.epam.courses.warehouse.rest;

import com.epam.courses.warehouse.model.DealTypes;
import com.epam.courses.warehouse.model.dto.ProductRecordDTO;
import com.epam.courses.warehouse.service.ProductRecordDtoService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductRecordDtoControllerTest {

    @InjectMocks
    private ProductRecordDtoController productRecordDtoController;

    @Mock
    private ProductRecordDtoService productRecordDtoService;

    private MockMvc mockMvc;

    @BeforeEach
    public void before(){
        mockMvc = MockMvcBuilders.standaloneSetup(productRecordDtoController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();
    }

    @AfterEach
    public void after(){
        verifyNoMoreInteractions(productRecordDtoService);
    }

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void getAll() throws Exception {

        when(productRecordDtoService.getAll()).thenReturn(Arrays.asList(create(0), create(1)));

        mockMvc.perform(
                MockMvcRequestBuilders.get("/records_dtos")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].recordId", Matchers.is(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].productName", Matchers.is("product 0")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].dealType", Matchers.is(DealTypes.fromInt(0).name())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].dealDate", Matchers.is(Date.valueOf("2011-11-11").getTime())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].quantityOfProduct", Matchers.is(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].recordId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].productName", Matchers.is("product 1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].dealType", Matchers.is(DealTypes.fromInt(1).name())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].dealDate", Matchers.is(Date.valueOf("2011-11-11").getTime())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].quantityOfProduct", Matchers.is(1)))
        ;

        verify(productRecordDtoService).getAll();
    }

    @Test
    void getAllInTimeInterval() throws Exception {
        when(productRecordDtoService.getAllInTimeInterval(any(Date.class), any(Date.class)))
                .thenReturn(Arrays.asList(create(0), create(1)));

        Date[] dates = new Date[2];
        dates[0] = Date.valueOf("2011-11-11");
        dates[1] = Date.valueOf("2011-11-11");

        MockHttpServletResponse response = mockMvc.perform(
                MockMvcRequestBuilders.post("/records_dtos")
                .content(objectMapper.writeValueAsString(dates))
                .contentType("application/json")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].recordId", Matchers.is(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].productName", Matchers.is("product 0")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].dealType", Matchers.is(DealTypes.fromInt(0).name())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].dealDate", Matchers.is(Date.valueOf("2011-11-11").getTime())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].quantityOfProduct", Matchers.is(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].recordId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].productName", Matchers.is("product 1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].dealType", Matchers.is(DealTypes.fromInt(1).name())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].dealDate", Matchers.is(Date.valueOf("2011-11-11").getTime())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].quantityOfProduct", Matchers.is(1)))
        .andReturn().getResponse();

        List<ProductRecordDTO> productRecordDTOList = objectMapper.readValue(response.getContentAsString(),
                new TypeReference<List<ProductRecordDTO>>(){});

        verify(productRecordDtoService).getAllInTimeInterval(Date.valueOf("2011-11-11"), Date.valueOf("2011-11-11"));
    }

    private ProductRecordDTO create(int index){
        return new ProductRecordDTO()
                .setRecordId(index)
                .setProductName("product " + index)
                .setDealType(DealTypes.fromInt(1&index))
                .setDealDate(Date.valueOf("2011-11-11"))
                .setQuantityOfProduct(index);
    }
}