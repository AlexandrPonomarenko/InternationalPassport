package com.InternationalPassport.controller;

import com.InternationalPassport.businessLayer.model.Customer;
import com.InternationalPassport.businessLayer.service.CustomerService;
import com.InternationalPassport.helper.SearchPassportForm;
import com.InternationalPassport.validation.CustomerLogInValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.thymeleaf.model.IModel;

import javax.jws.WebParam;
import javax.validation.Valid;


@Controller("LoginController")
public class LoginController {
    private final Logger logger = LogManager.getLogger(LoginController.class);

    private SearchPassportForm searchPassportForm = new SearchPassportForm();

    @Autowired
    private CustomerService customerService;

//    @Autowired
//    private CustomerLogInValidator customerLogInValidator;

    @RequestMapping(value = "/logIn", method = RequestMethod.GET)
    public String logIn(Model model) {
        model.addAttribute("searchPassportForm", searchPassportForm);
        model.addAttribute("customer", new Customer());
        return "logIn";
    }

    @RequestMapping(value = "/logIn", method = RequestMethod.POST)
    public String logInPost(@ModelAttribute Customer customer, SearchPassportForm searchPassportForm,
                            Model model) {
        logger.debug("login customer " +  customer.toStringLogin());
        logger.debug("login searchPassportForm " +  searchPassportForm.toString());

        if (!IsLogin(customer)) {
            model.addAttribute("loginError", true);
            model.addAttribute("searchPassportForm", searchPassportForm);
            return "logIn";
        }

        return "redirect:/home";
    }

    private Boolean IsLogin (Customer customer) {
        boolean result = false;
        Customer fCus = customerService.findByLogin(customer.getLogin());
        logger.debug(fCus.getPassword().equals(customer.getPassword()) + "LOGIIINNN" + customer.toStringLogin());
        if (fCus != null && fCus.getLogin() != null && fCus.getPassword().equals(customer.getPassword())) {
            logger.debug("CUSTOMER FROM BD - " + fCus.toStringLogin());
            result = true;
        }
        return result;
    }
}
