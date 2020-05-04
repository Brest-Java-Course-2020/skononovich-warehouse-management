package com.epam.courses.warehouse.web_app.validators;

import com.epam.courses.warehouse.model.ProductRecord;
import com.epam.courses.warehouse.service_rest.ProductDtoServiceRest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RecordValidatorTest {

    @InjectMocks
    private RecordValidator recordValidator;

    @Mock
    private ProductDtoServiceRest productDtoServiceRest;

    private BindingResult bindingResult;

    @Mock
    private ProductRecord productRecord;

    @BeforeEach
    public void before() {
        bindingResult = new BeanPropertyBindingResult(productRecord, "productRecord");
    }

    @Test
    void shouldValidatorSupportsProductRecordClass() {
        assertTrue(recordValidator.supports(ProductRecord.class));
    }

    @Test
    void shouldRejectIfQuantityOfProductIsEmpty() {
        when(productRecord.getQuantityOfProduct()).thenReturn(null);
        when(productRecord.getProductRecordDate()).thenReturn(null);

        recordValidator.validate(productRecord, bindingResult);

        assertTrue(bindingResult.hasFieldErrors("quantityOfProduct"));
    }

    @Test
    void shouldRejectIfDateIsEmpty() {
        when(productRecord.getProductRecordDate()).thenReturn(null);

        recordValidator.validate(productRecord, bindingResult);

        assertTrue(bindingResult.hasFieldErrors("productRecordDate"));
    }

    @Test
    void shouldRejectIfProductQuantityLessThenZero() {
        when(productRecord.getQuantityOfProduct()).thenReturn(-1);

        recordValidator.validate(productRecord, bindingResult);

        assertTrue(bindingResult.hasFieldErrors("quantityOfProduct"));
    }

    @Test
    void shouldRejectIfNotEnoughProducts() {
        when(productRecord.getQuantityOfProduct()).thenReturn(1);
        when(productDtoServiceRest.enoughProducts(productRecord)).thenReturn(false);

        recordValidator.validate(productRecord, bindingResult);

        assertTrue(bindingResult.hasFieldErrors("quantityOfProduct"));
    }

    @Test
    void shouldRejectIfDateLessThenMinimalDate(){
        LocalDate date = LocalDate.MIN;
        when(productRecord.getProductRecordDate()).thenReturn(Date.valueOf(date));

        recordValidator.validate(productRecord, bindingResult);

        assertTrue(bindingResult.hasFieldErrors("productRecordDate"));
    }

    @Test
    void shouldRejectIfDateMoreThenCurrentDate(){
        LocalDate date = LocalDate.now().plusMonths(3);
        when(productRecord.getProductRecordDate()).thenReturn(Date.valueOf(date));

        recordValidator.validate(productRecord, bindingResult);

        assertTrue(bindingResult.hasFieldErrors("productRecordDate"));
    }
}