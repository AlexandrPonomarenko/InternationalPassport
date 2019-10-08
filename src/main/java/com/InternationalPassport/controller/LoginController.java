package com.InternationalPassport.controller;

import com.InternationalPassport.businessLayer.model.Customer;
import com.InternationalPassport.businessLayer.service.CustomerService;
import com.InternationalPassport.helper.SearchPassportForm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller("LoginController")
public class LoginController {
    private final Logger logger = LogManager.getLogger(LoginController.class);

    private SearchPassportForm searchPassportForm = new SearchPassportForm();

//    @Autowired
//    private CustomerService customerService;

    @RequestMapping(value = "/logIn", method = RequestMethod.GET)
    public String logIn(Model model) {
        model.addAttribute("searchPassportForm", searchPassportForm);
        model.addAttribute("customer", new Customer());
        logger.debug("COME IN LOIN CONTROLLER");
        return "logIn";
    }

    @RequestMapping(value = "/logIn", method = RequestMethod.POST)
    public void logInPost(@ModelAttribute Customer customer, SearchPassportForm searchPassportForm) {
        logger.debug("login customer " +  customer.toStringLogin());
        logger.debug("login searchPassportForm " +  searchPassportForm.toString());

    }
}
