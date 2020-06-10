package com.InternationalPassport.controller;

import com.InternationalPassport.helper.SearchPassportForm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorController {

    final private Logger logger = LogManager.getLogger(ErrorController.class);
    private SearchPassportForm searchPassportForm = new SearchPassportForm();

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String showErrorPage(Model model, HttpServletRequest req) {

        model.addAttribute("directQuery", req.getAttribute("directQuery"));
        model.addAttribute("searchPassportForm", searchPassportForm);
        return "errorPage/error";
    }
}
