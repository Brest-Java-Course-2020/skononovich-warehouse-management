package com.epam.courses.warehouse.rest;

import com.epam.courses.warehouse.model.dto.ProductDto;
import com.epam.courses.warehouse.service.ProductDtoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class ProductDtoController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductDtoController.class);

    private ProductDtoService productDtoService;

    public ProductDtoController(ProductDtoService productDtoService) {
        this.productDtoService = productDtoService;
    }

    @GetMapping(value = "/products_dtos")
    public Collection<ProductDto> products(){
        LOGGER.debug("ProductController:products");

        return productDtoService.getAllProductsWithSummaryCount();
    }
}
