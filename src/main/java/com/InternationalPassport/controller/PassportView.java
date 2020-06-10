package com.InternationalPassport.controller;

import com.InternationalPassport.businessLayer.model.Customer;
import com.InternationalPassport.businessLayer.service.CustomerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PassportView {
    private static final Logger logger = LogManager.getLogger(PassportView.class);

    @Autowired
    CustomerService customerService;

    @RequestMapping(value = "/passportView", method = RequestMethod.GET)
    public String showPassportPage() {
        return "passportView";
    }

    @RequestMapping(value = "/passportView", method = RequestMethod.POST)
    public String showPassportPagePost(@ModelAttribute Customer customer, Model model, RedirectAttributes redirectAttributes) {

        Customer customer1 = findPassport(customer);
        if (customer1 != null && customer1.getPhotos().size() > 0) {
            redirectAttributes.addFlashAttribute("customer", customer1);
            redirectAttributes.addFlashAttribute("photo", customer1.getPhotos().get(0));
        } else {
            redirectAttributes.addFlashAttribute("notFound", "Sorry, customer not found.");
        }

        return "redirect:passportView";
    }

    private Customer findPassport(Customer customer) {
        Customer customerF = null;
        try {
            customerF = customerService.findByLoginInitAll(customer.getLogin());
        } catch (DataAccessException e ) {
            logger.error("CUSTOMER NOT FOUND" +  e);
        }
        return customerF;
    }


}
