package com.epam.courses.warehouse.web_app;

import com.epam.courses.warehouse.model.DealTypes;
import com.epam.courses.warehouse.model.ProductRecord;
import com.epam.courses.warehouse.model.filter.ProductRecordDateInterval;
import com.epam.courses.warehouse.service_rest.ProductRecordDtoServiceRest;
import com.epam.courses.warehouse.service_rest.ProductRecordServiceRest;
import com.epam.courses.warehouse.service_rest.ProductServiceRest;
import com.epam.courses.warehouse.web_app.validators.ProductRecordDateIntervalValidator;
import com.epam.courses.warehouse.web_app.validators.RecordValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Arrays;

@Controller
public class RecordController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RecordController.class);

    private final ProductRecordDtoServiceRest productRecordDtoServiceRest;

    private final ProductRecordServiceRest productRecordServiceRest;

    private final ProductServiceRest productService;

    @Autowired
    RecordValidator recordValidator;

    @Autowired
    ProductRecordDateIntervalValidator productRecordDateIntervalValidator;

    public RecordController(ProductRecordServiceRest productRecordService,
                            ProductRecordDtoServiceRest productRecordDtoService,
                            ProductServiceRest productService){
        this.productRecordServiceRest = productRecordService;
        this.productRecordDtoServiceRest = productRecordDtoService;
        this.productService = productService;
    }

    /**
     * Go to product circulation page.
     * @param model Model.
     * @return View name.
     * @throws ParseException
     */
    @GetMapping("/circulation")
    public final String records(Model model) throws ParseException {
        LOGGER.debug("RecordController:records");

        ProductRecordDateInterval interval = new ProductRecordDateInterval();

        model.addAttribute("interval", interval);
        model.addAttribute("records", productRecordDtoServiceRest.getAll());
        return "circulation";
    }

    /**
     * Go to product circulation page
     * @param interval <code>ProductRecordDateInterval</code>.
     * @param result <code>BindingResult</code>.
     * @param model <code>Model</code>.
     * @return View name.
     */
    @GetMapping("/filter")
    public final String filterRecords(
            @Valid @ModelAttribute("interval") ProductRecordDateInterval interval,
            BindingResult result,
            Model model) {
        LOGGER.debug("filterRecords({},{})", interval, result);


        productRecordDateIntervalValidator.validate(interval, result);
        if(result.hasErrors()){
            model.addAttribute("records", productRecordDtoServiceRest.getAll());
        } else {
            model.addAttribute("interval", interval);
            model.addAttribute("records", productRecordDtoServiceRest
                    .getAllInTimeInterval(
                            new Date(interval.getStartInterval().getTime()),
                            new Date(interval.getEndInterval().getTime())
                    )
            );
        }

        return "circulation";
    }

    /**
     * Go to product records page.
     * @param model Model.
     * @return View name.
     */
    @GetMapping(value = "/record")
    public String getRecordPage(Model model){
        model.addAttribute("products", productService.getAll());
        model.addAttribute("dealTypes", Arrays.asList(DealTypes.values()));
        model.addAttribute("productRecord", new ProductRecord()
                .setProductRecordId(1)
                .setDealType(DealTypes.GETTING)
                .setProductRecordDate(Date.valueOf(LocalDate.now())));

        return "record";
    }

    /**
     * Go to create product record page.
     * @param productRecord <code>ProductRecord</code>.
     * @param result <code>BindingResult</code>.
     * @param model <code>Model</code>.
     * @return View name.
     */
    @PostMapping(value = "/record")
    public String createRecord(@Valid ProductRecord productRecord, BindingResult result, Model model) {
        LOGGER.debug("createRecord({}{})", productRecord, result);
        recordValidator.validate(productRecord, result);

        if(result.hasErrors()){
            model.addAttribute("products", productService.getAll());
            model.addAttribute("dealTypes", Arrays.asList(DealTypes.values()));
            return "record";
        } else {
            productRecordServiceRest.create(productRecord);
            return "redirect:/circulation";
        }
    }
}
