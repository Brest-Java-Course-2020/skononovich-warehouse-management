package com.epam.courses.warehouse.rest;

import com.epam.courses.warehouse.model.dto.ProductDto;
import com.epam.courses.warehouse.service.ProductDtoService;
import org.hamcrest.Matchers;
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

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductDtoControllerTest {

    @InjectMocks
    private ProductDtoController productDtoController;

    @Mock
    private ProductDtoService productDtoService;

    private MockMvc mockMvc;

    @BeforeEach
    public void before(){
        mockMvc = MockMvcBuilders.standaloneSetup(productDtoController).build();
    }

    public void after(){
        verifyNoMoreInteractions(productDtoService);
    }

    @Test
    public void shouldGetAllProducts() throws Exception {
        when(productDtoService.getAllProductsWithSummaryCount()).thenReturn(Arrays.asList(create(0), create(1)));

        mockMvc.perform(
                MockMvcRequestBuilders.get("/products_dtos")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].productId", Matchers.is(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].productName", Matchers.is("product 0")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].productSumCount", Matchers.is(100)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].productId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].productName", Matchers.is("product 1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].productSumCount", Matchers.is(101)))
        ;

        verify(productDtoService).getAllProductsWithSummaryCount();
    }

    private ProductDto create(int index){
        return new ProductDto()
                .setProductId(index)
                .setProductName("product " + index)
                .setProductSumCount(index + 100);
    }
}