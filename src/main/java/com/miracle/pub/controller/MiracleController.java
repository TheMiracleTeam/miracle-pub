package com.miracle.pub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MiracleController {

    @RequestMapping("/")
    public ModelAndView index(ModelMap modelMap) {
        return new ModelAndView("index");
    }
}
