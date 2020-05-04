package com.epam.courses.warehouse.web_app;

import com.epam.courses.warehouse.model.Product;
import com.epam.courses.warehouse.model.dto.ProductDto;
import com.epam.courses.warehouse.service_rest.ProductServiceRest;
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

import java.util.Optional;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:app-context-test.xml"})
class ProductControllerIT {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Autowired
    private ProductServiceRest productServiceRest;

    @BeforeEach
    public void before(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    void shouldGetProducts() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.get("/products")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("products"))
        ;
    }

    @Test
    void shouldGoToProductPage() throws Exception {
        when(productServiceRest.getById(1)).thenReturn(Optional.of(createProduct(1)));
        mockMvc.perform(
                MockMvcRequestBuilders.get("/product/1")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("updateProduct"))
                .andExpect(model().attribute("product", hasProperty("productId", is(1))))
                .andExpect(model().attribute("product", hasProperty("productName", is("Product 1"))))
        ;
    }

    @Test
    void shouldUpdateProduct() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/product/1")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("productId", "1")
                .param("productName", "Product 1")
                .sessionAttr("product", createProduct(1))
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/products"))
                .andExpect(redirectedUrl("/products"));
    }

    @Test
    void shouldGoToAddProductPage() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/product")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("product"));
    }

    @Test
    void shouldAddProduct() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/product")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("productId", "1")
                        .param("productName", "Product 1")
                        .sessionAttr("product", createProduct(1))
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/products"))
                .andExpect(redirectedUrl("/products"));
    }

    @Test
    void shouldDeleteProductById() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/product/delete/1")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/products"))
                .andExpect(redirectedUrl("/products"));
    }

    private Product createProduct(int i) {
        return  new Product().setProductId(i).setProductName("Product " + i );
    }

    private ProductDto createProductDto(int i) {
        return new ProductDto().setProductId(i).setProductName("Product " + i).setProductSumCount(i);
    }
}