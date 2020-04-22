package com.epam.courses.warehouse.web_app.validators;

import com.epam.courses.warehouse.model.Product;
import com.epam.courses.warehouse.model.dto.ProductDto;
import com.epam.courses.warehouse.service_rest.ProductDtoServiceRest;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;

import static com.epam.courses.warehouse.model.constants.ProductConstants.PRODUCT_NAME_SIZE;

public class ProductValidator implements Validator {

    ProductDtoServiceRest productDtoServiceRest;
    @Override
    public boolean supports(Class<?> clazz) {
        return Product.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmpty(errors, "productName", "productName.empty");
        Product product = (Product) target;

        if (StringUtils.hasLength(product.getProductName())
                && product.getProductName().length() > PRODUCT_NAME_SIZE) {
            errors.rejectValue("productName", "productName.maxSize");
        }
        if (productExist(product)) {
            errors.rejectValue("productName", "productName.isExist");
        }
        if (true) {//TODO
            errors.rejectValue("productName", "productName.cantDelete");
        }
    }

    private boolean productExist(Product product){
        List<ProductDto> productDtoList = productDtoServiceRest.getAllProductsWithSummaryCount();

        for(ProductDto productDto : productDtoList){
            if(productDto.getProductName().equalsIgnoreCase(product.getProductName()))
                return true;
        }
        return false;
    }
}
