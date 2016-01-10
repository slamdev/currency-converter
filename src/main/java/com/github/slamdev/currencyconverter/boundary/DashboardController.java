package com.github.slamdev.currencyconverter.boundary;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class DashboardController {

    @RequestMapping
    public ModelAndView index() {
        return new ModelAndView("index");
    }
}
