package com.epam.courses.warehouse.rest;

import com.epam.courses.warehouse.model.dto.ProductRecordDTO;
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

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:app-context-test.xml")
@Rollback
@Transactional
class ProductRecordDtoControllerIT {

    public static final String RECORDS_DTOS_ENDPOINT = "/records_dtos";

    @Autowired
    private ProductRecordDtoController productRecordDtoController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void before(){
        mockMvc = MockMvcBuilders.standaloneSetup(productRecordDtoController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();
    }
    @Test
    void shouldGetAllRecords() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(
                MockMvcRequestBuilders.get(RECORDS_DTOS_ENDPOINT)
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertNotNull(response);

        List<ProductRecordDTO> productRecordDTO = objectMapper.readValue(response.getContentAsString(),
                new TypeReference<List<ProductRecordDTO>>(){});

        assertNotNull(productRecordDTO);
        }

    @Test
    void getAllInTimeInterval() throws Exception {

        Date[] dates = new Date[2];
        dates[0] = Date.valueOf("2011-11-11");
        dates[1] = Date.valueOf("2011-11-11");

        MockHttpServletResponse response = mockMvc.perform(
                MockMvcRequestBuilders.post(RECORDS_DTOS_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dates))
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        List<ProductRecordDTO> productRecordDTO = objectMapper.readValue(response.getContentAsString(),
                new TypeReference<List<ProductRecordDTO>>(){});

        assertNotNull(productRecordDTO);
    }
}