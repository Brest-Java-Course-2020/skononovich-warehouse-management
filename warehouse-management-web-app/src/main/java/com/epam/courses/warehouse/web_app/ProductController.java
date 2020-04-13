package com.epam.courses.warehouse.web_app;

import com.epam.courses.warehouse.model.Product;
import com.epam.courses.warehouse.service.ProductDtoService;
import com.epam.courses.warehouse.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

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

    @GetMapping(value = "/product/{id}")
    public final String goToProductPage(@PathVariable Integer id, Model model){
    LOGGER.debug("goToProductPage({}, {})", id, model );

    Optional<Product> productOptional = productService.getById(id);

    if(productOptional.isPresent()){
        model.addAttribute("isNew", false);
        model.addAttribute("department", productOptional.get());
        return "product";
    } else {
        // TODO department not found - pass error message as parameter or handle not found error
        return "redirect:products";
    }
    }

    /**
     * Update department.
     *
     * @param product product with filled data.
     * @param result     binding result
     * @return view name
     */
    @PostMapping(value = "/product/{id}")
    public String updateProduct(Product product,
                                   BindingResult result) {

        LOGGER.debug("updateProduct({}, {})", product, result);
       // departmentValidator.validate(department, result);
        if (result.hasErrors()) {
            return "product";
        } else {
            this.productService.update(product);
            return "redirect:/products";
        }
    }

    /**
     * Goto add product page.
     *
     * @return view name
     */
    @GetMapping(value = "/product")
    public final String gotoAddProductPage(Model model) {

        LOGGER.debug("gotoAddProductPage({})", model);
        model.addAttribute("product", new Product());
        return "product";
    }

    /**
     * Persist new department into persistence storage.
     *
     * @param product new product with filled data.
     * @param result     binding result.
     * @return view name
     */
    @PostMapping(value = "/product")
    public String addProduct(Product product,
                                BindingResult result) {

        LOGGER.debug("addProduct({}, {})", product, result);
            productService.create(product);
            return "redirect:/products";
//        }
    }


    /**
     * Delete department.
     *
     * @return view name
     */
    @GetMapping(value = "/product/{id}/delete")
    public final String deleteProductById(@PathVariable Integer id, Model model) {

        LOGGER.debug("delete({},{})", id, model);
        productService.delete(id);
        return "redirect:/products";
    }
}
