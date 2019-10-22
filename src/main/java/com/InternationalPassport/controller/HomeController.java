package com.InternationalPassport.controller;

import com.InternationalPassport.helper.SearchPassportForm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
    private static final Logger logger = LogManager.getLogger(HomeController.class);

    private SearchPassportForm searchPassportForm = new SearchPassportForm();

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute("searchPassportForm", searchPassportForm);
        logger.debug("FROM HOME");
        return "home";
    }
}
