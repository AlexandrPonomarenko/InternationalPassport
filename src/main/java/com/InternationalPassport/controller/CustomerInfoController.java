package com.InternationalPassport.controller;

import com.InternationalPassport.businessLayer.model.Address;
import com.InternationalPassport.businessLayer.model.Customer;
import com.InternationalPassport.businessLayer.model.Passport;
import com.InternationalPassport.businessLayer.service.AddressService;
import com.InternationalPassport.businessLayer.service.CustomerService;
import com.InternationalPassport.businessLayer.service.PassportService;
import com.InternationalPassport.helper.SearchPassportForm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CustomerInfoController {
    public static final Logger logger = LogManager.getLogger(CustomerInfoController.class);
    private Customer customer = null;
    private Address address = null;
    private Passport passport = null;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private PassportService passportService;

    private SearchPassportForm searchPassportForm = new SearchPassportForm();

//    @RequestMapping(value = "/control/customer", method = RequestMethod.GET)
//    public ModelAndView infoCustomer(@RequestParam(required = false) String login, ModelAndView model) {
//        model.addObject("searchPassportForm", searchPassportForm);
//        model.addObject("customers", getCustomer(login));
//        model.setViewName("customer");
//        logger.debug("PARAM " + login);
//        return model;
//    }

    @RequestMapping(value = {"/control/customer", "/control/customer/{login}", "/ceo/customer", "/ceo/customer/{login}"}, method = RequestMethod.GET)
    public ModelAndView infoCustomer(@PathVariable(required = false) String login, ModelAndView model) {
        logger.debug("PARAM " + login);
        customer = getCustomer(login);
        address = getAddress(customer.getId());
        passport = getPassport(customer.getId());
        model.addObject("searchPassportForm", searchPassportForm);
        model.addObject("customer", customer);
        model.addObject("address", address);
        model.addObject("passport", passport);
        model.setViewName("customer");
        return model;
    }

    private Customer getCustomer(String login) {
        Customer customer = customerService.findByLogin(login);
        logger.debug("getCustomer  " + customer);
        return  customer;
    }

    private Address getAddress(int customerId) {
        Address address = addressService.findById(customerId);
        logger.debug("getAddress  " + address);
        return  address;
    }

    private Passport getPassport(int customerId) {
        Passport passport = passportService.findById(customerId);
        logger.debug("getPassport  " + passport);
        return  passport;
    }
}
