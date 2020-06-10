package com.InternationalPassport.controller;

import com.InternationalPassport.businessLayer.model.Customer;
import com.InternationalPassport.businessLayer.model.Passport;
import com.InternationalPassport.businessLayer.service.CustomerService;
import com.InternationalPassport.helper.SearchPassportForm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.jws.WebParam;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller("MainController")
public class MainController {

    private final Logger logger = LogManager.getLogger(MainController.class);
    private  SearchPassportForm searchPassportForm = new SearchPassportForm();

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = {"/", "/main"} , method = RequestMethod.GET)
    public String mainPage(Model model, HttpSession session) {
        List<Customer> list = getUsersNames();
        model.addAttribute("users", list);
        model.addAttribute("searchPassportForm", searchPassportForm);

        return "main";
    }

    private List<Customer> getUsersNames() {
        List<Customer> customers = customerService.findAll();
        return customers;
    }

    @RequestMapping(value = "/main", method = RequestMethod.POST)
    public void searchUser(@ModelAttribute SearchPassportForm searchPassportForm) {
        logger.debug("searchUser " + searchPassportForm.getSeria());
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String about(Model model) {
        return "about";
    }
}
