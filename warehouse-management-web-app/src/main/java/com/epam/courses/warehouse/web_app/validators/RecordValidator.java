package com.epam.courses.warehouse.web_app.validators;

import com.epam.courses.warehouse.model.ProductRecord;
import com.epam.courses.warehouse.model.dto.ProductDto;
import com.epam.courses.warehouse.service_rest.ProductDtoServiceRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static com.epam.courses.warehouse.model.constants.ProductRecordConstants.MINIMAL_DATE;

public class RecordValidator implements Validator {
    @Autowired
    ProductDtoServiceRest productDtoServiceRest;

    @Override
    public boolean supports(Class<?> clazz) {
        return ProductRecord.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmpty(errors, "quantityOfProduct", "productQuantity.empty");

        ProductRecord productRecord = (ProductRecord) target;

        if (productRecord.getQuantityOfProduct() <= 0) {
            errors.rejectValue("quantityOfProduct", "productQuantity.minSize");
        }
        if (!enoughProducts(productRecord)) {
            errors.rejectValue("quantityOfProduct", "productQuantity.notEnough");
        }
        if (Date.valueOf(MINIMAL_DATE).after(productRecord.getProductRecordDate())
                || productRecord.getProductRecordDate().after(Date.valueOf(LocalDate.now()))) {
            errors.rejectValue("productRecordDate", "productRecordDate.impossible");
        }
    }

    private boolean enoughProducts(ProductRecord productRecord){
        List<ProductDto> productDtoList = productDtoServiceRest.getAllProductsWithSummaryCount();

        for (ProductDto product : productDtoList){
            if(product.getProductId().equals(productRecord.getProductId())
                    && product.getProductSumCount() >= productRecord.getQuantityOfProduct())
                return true;
        }
        return false;
    }
}