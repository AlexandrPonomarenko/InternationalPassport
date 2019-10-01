package com.InternationalPassport.controller;

import com.InternationalPassport.businessLayer.model.Customer;
import com.InternationalPassport.helper.SearchPassportForm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("SignUpController")
public class SignUpController {

    private final Logger logger = LogManager.getLogger(SignUpController.class);

    private SearchPassportForm searchPassportForm = new SearchPassportForm();

//    @Autowired
//    private CustomerService customerService;

    @RequestMapping(value = "/signUp", method = RequestMethod.GET)
    public String signUp(Model model) {
        model.addAttribute("searchPassportForm", searchPassportForm);
        model.addAttribute("customer", new Customer());
        return "signUp";
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public void regCustomer(@ModelAttribute Customer customer, SearchPassportForm searchPassportForm) {
        logger.debug("SIGH-UP login customer " +  customer.toStringLogin());
        logger.debug("SIGH-UP login searchPassportForm " +  searchPassportForm.toString());
    }

}
