package com.miracle.pub.controller;

import com.miracle.pub.util.MessageUtil;
import com.miracle.pub.util.SHA1;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RequestMapping("miracle/pub")
@Controller
public class MiracleController {

    private String Token = "zhbtest";

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public ModelAndView index(ModelMap modelMap) {
        return new ModelAndView("index");
    }

}
