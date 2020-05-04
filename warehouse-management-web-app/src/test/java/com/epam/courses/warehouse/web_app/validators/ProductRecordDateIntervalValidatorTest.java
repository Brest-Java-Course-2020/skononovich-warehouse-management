package com.epam.courses.warehouse.web_app.validators;

import com.epam.courses.warehouse.model.filter.ProductRecordDateInterval;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductRecordDateIntervalValidatorTest {

    @InjectMocks
    private ProductRecordDateIntervalValidator dateIntervalValidator;

    private BindingResult bindingResult;

    @Mock
    private ProductRecordDateInterval dateInterval;

    @BeforeEach
    public void before() {
        bindingResult = new BeanPropertyBindingResult(dateInterval, "dateInterval");
    }

    @Test
    void shouldValidatorSupportsProductRecordDateInterval() {
        assertTrue(dateIntervalValidator.supports(ProductRecordDateInterval.class));
    }

    @Test
    void shouldRejectEmptyStartInterval() {
        when(dateInterval.getStartInterval()).thenReturn(null);

        dateIntervalValidator.validate(dateInterval, bindingResult);

        assertTrue(bindingResult.hasFieldErrors("startInterval"));
    }

    @Test
    void shouldRejectEmptyEndInterval() {
        when(dateInterval.getStartInterval()).thenReturn(null);

        dateIntervalValidator.validate(dateInterval, bindingResult);

        assertTrue(bindingResult.hasFieldErrors("startInterval"));
    }
}