package com.epam.courses.warehouse.web_app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Home controller.
 */
@Controller
public class HomeController {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    /**
     * Redirect to products page.
     * @return View name.
     */
    @GetMapping("/")
    public final String redirectToProducts(){
        LOGGER.debug("redirectToProducts()");

        return "redirect:/products";
    }
}
