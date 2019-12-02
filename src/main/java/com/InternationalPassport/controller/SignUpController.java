package com.InternationalPassport.controller;

import com.InternationalPassport.businessLayer.model.Address;
import com.InternationalPassport.businessLayer.model.Customer;
import com.InternationalPassport.businessLayer.model.Role;
import com.InternationalPassport.businessLayer.service.CustomerService;
import com.InternationalPassport.helper.SearchPassportForm;
//import com.sun.org.apache.xpath.internal.operations.String;
import com.InternationalPassport.validation.CustomerValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Controller("SignUpController")
public class SignUpController {

    private final Logger logger = LogManager.getLogger(SignUpController.class);

    private SearchPassportForm searchPassportForm = new SearchPassportForm();

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerValidator customerValidator;

    @RequestMapping(value = "/signUp", method = RequestMethod.GET)
    public String signUp(Model model) {
        model.addAttribute("searchPassportForm", searchPassportForm);
        model.addAttribute("customer", new Customer());
        return "signUp";
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public String regCustomer(@Valid @ModelAttribute("customer") Customer customer
                                , BindingResult bindingResult, Model model) {

        logger.debug("SIGH-UP login customer " +  customer.toStringLogin());

        customerValidator.validate(customer, bindingResult);
//        logger.debug("SIGH-UP login customer " +  customer.toStringLogin());
//        logger.debug("SIGH-UP login searchPassportForm " +  searchPassportForm.toString());

        if (bindingResult.hasErrors()) {
            model.addAttribute("searchPassportForm", new SearchPassportForm());
            return "signUp";
        }

        saveCustomer(customer);
        return "redirect:/home";
    }

    private void saveCustomer(Customer customer) {
        if (customer != null) {
            Role role = new Role("User");
            customer.setRole(role);
            role.getCustomers().add(customer);
            customerService.persist(customer);
        }
    }


    //TODO just for tests
    @RequestMapping(value = "/testfile", method = RequestMethod.GET)
    public String test(Model model) {
        model.addAttribute("searchPassportForm", searchPassportForm);
        model.addAttribute("test", new String());
        return "testfile";
    }

    @RequestMapping(value = "/testfile", method = RequestMethod.POST)
    public String getTest(@RequestParam(value = "test", required = false) String test, SearchPassportForm searchPassportForm) {
        logger.debug("TESZT STRING " + test);
        return "testfile";
    }

}
