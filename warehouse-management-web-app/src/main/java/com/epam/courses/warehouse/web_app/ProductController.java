package com.epam.courses.warehouse.web_app;

import com.epam.courses.warehouse.model.Product;
import com.epam.courses.warehouse.service_rest.ProductDtoServiceRest;
import com.epam.courses.warehouse.service_rest.ProductServiceRest;
import com.epam.courses.warehouse.web_app.validators.ProductValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Optional;

/**
 * Product web controller.
 */
@Controller
public final class ProductController {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    /**
     * ProductServiceRest.
     */
    private ProductServiceRest productService;

    /**
     * ProductDtoServiceRest.
     */
    private ProductDtoServiceRest productDtoService;

    /**
     * ProductValidator.
     */
    @Autowired
    ProductValidator productValidator;

    /**
     * Constructor for ProductController.
     * @param productService ProductService.
     * @param productDtoService ProductDtoService.
     */
    public ProductController(final ProductServiceRest productService, final ProductDtoServiceRest productDtoService) {
        this.productService = productService;
        this.productDtoService = productDtoService;
    }

    /**
     * Go to products list page.
     * @param model Model.
     * @return View name.
     */
    @GetMapping("/products")
    public String products(Model model) {
        LOGGER.debug("products()");

        model.addAttribute("products", productDtoService.getAllProductsWithSummaryCount());
        return "products";
    }

    /**
     * Go to product page.
     * @param id Product id.
     * @param model Model.
     * @return View name.
     */
    @GetMapping(value = "/product/{id}")
    public String goToProductPage(@PathVariable Integer id, Model model) {
    LOGGER.debug("goToProductPage({}, {})", id, model );

    Optional<Product> productOptional = productService.getById(id);

    if(productOptional.isPresent()){
        model.addAttribute("product", productOptional.get());
        return "updateProduct";
    } else {
        return "redirect:products";
    }
    }

    /**
     * Update product.
     *
     * @param product product with filled data.
     * @param result binding result
     * @return view name
     */
    @PostMapping(value = "/product/{id}")
    public String updateProduct(@Valid Product product,
                                   BindingResult result) {

        LOGGER.debug("updateProduct({}, {})", product, result);
        productValidator.validate(product, result);
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
    public String gotoAddProductPage(Model model) {

        LOGGER.debug("gotoAddProductPage({})", model);
        model.addAttribute("product", new Product());
        return "product";
    }

    /**
     * Persist new product into persistence storage.
     *
     * @param product new product with filled data.
     * @param result     binding result.
     * @return view name
     */
    @PostMapping(value = "/product")
    public String addProduct(@Valid Product product,
                                BindingResult result) {
        LOGGER.debug("addProduct({}, {})", product, result);
        productValidator.validate(product, result);
        if(result.hasErrors()) {
            return "product";
        } else {
            productService.create(product);
            return "redirect:/products";
        }
    }


    /**
     * Delete product.
     *
     * @return view name
     */
    @GetMapping(value = "/product/delete/{id}")
    public String deleteProductById(@PathVariable Integer id, Model model) {

        LOGGER.debug("delete({},{})", id, model);
        productService.delete(id);
        return "redirect:/products";
    }


}
