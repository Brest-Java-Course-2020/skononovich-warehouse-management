package com.epam.courses.warehouse.web_app;

import com.epam.courses.warehouse.model.DealTypes;
import com.epam.courses.warehouse.model.ProductRecord;
import com.epam.courses.warehouse.model.filter.ProductRecordDateInterval;
import com.epam.courses.warehouse.service_rest.ProductRecordDtoServiceRest;
import com.epam.courses.warehouse.service_rest.ProductRecordServiceRest;
import com.epam.courses.warehouse.service_rest.ProductServiceRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;

@Controller
public class RecordController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RecordController.class);

    private final ProductRecordDtoServiceRest productRecordDtoServiceRest;

    private final ProductRecordServiceRest productRecordServiceRest;

    private final ProductServiceRest productService;

    public RecordController(ProductRecordServiceRest productRecordService,
                            ProductRecordDtoServiceRest productRecordDtoService,
                            ProductServiceRest productService){
        this.productRecordServiceRest = productRecordService;
        this.productRecordDtoServiceRest = productRecordDtoService;
        this.productService = productService;
    }

    @GetMapping("/circulation")
    public final String records(Model model){
        LOGGER.debug("RecordController:records");

        ProductRecordDateInterval interval = new ProductRecordDateInterval();

        model.addAttribute("interval", interval);
        model.addAttribute("records", productRecordDtoServiceRest.getAll());
        return "circulation";
    }

    @GetMapping("/filter")
    public final String filterRecords(
            @ModelAttribute("interval") ProductRecordDateInterval interval,
            Model model) {

        model.addAttribute("interval", interval);
        model.addAttribute("records", productRecordDtoServiceRest
                .getAllInTimeInterval(
                interval.getStartInterval(),
                interval.getEndInterval()));
        return "circulation";
    }

    @GetMapping(value = "/record")
    public final String getRecordPage(Model model){
        model.addAttribute("products", productService.getAll());
        model.addAttribute("dealTypes", Arrays.asList(DealTypes.values()));
        model.addAttribute("record", new ProductRecord().setProductRecordId(1).setDealType(DealTypes.GETTING));

        return "record";
    }

    @PostMapping(value = "/record")
    public String createRecord(ProductRecord record) {

        LOGGER.debug("createRecord({})", record);


        productRecordServiceRest.create(record);

        return "redirect:/circulation";
    }


}
