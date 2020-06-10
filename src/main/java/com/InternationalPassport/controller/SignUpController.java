package com.InternationalPassport.controller;

import com.InternationalPassport.businessLayer.model.Address;
import com.InternationalPassport.businessLayer.model.Customer;
import com.InternationalPassport.businessLayer.model.Role;
import com.InternationalPassport.businessLayer.service.CustomerService;
import com.InternationalPassport.businessLayer.service.email.EmailService;
import com.InternationalPassport.helper.SearchPassportForm;
//import com.sun.org.apache.xpath.internal.operations.String;
import com.InternationalPassport.validation.CustomerValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.mail.javamail.JavaMailSender;
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

    @Autowired
    private EmailService emailService;

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

        if (bindingResult.hasErrors()) {
            model.addAttribute("searchPassportForm", new SearchPassportForm());
            return "signUp";
        }

        saveCustomer(customer);
        emailService.sendRegistrationEmail("furriets@gmail.com", customer.getName(), "google.com");
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
}
