package com.epam.courses.warehouse.web_app;

import com.epam.courses.warehouse.service.ProductDtoService;
import com.epam.courses.warehouse.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    private final ProductDtoService productDtoService;

    public ProductController(ProductService productService, ProductDtoService productDtoService){
        this.productService = productService;
        this.productDtoService = productDtoService;
    }

    @GetMapping("/products")
    public final String products(Model model){
        LOGGER.debug("ProductController:products");

        model.addAttribute("products", productDtoService.getAllProductsWithSummaryCount());
        return "products";
    }
}
