package com.InternationalPassport.controller;

import com.InternationalPassport.businessLayer.model.Customer;
import com.InternationalPassport.businessLayer.service.CustomerService;
import com.InternationalPassport.helper.SearchPassportForm;
import com.InternationalPassport.security.UserDetailsImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;


@Controller("LoginController")
public class LoginController {
    private final Logger logger = LogManager.getLogger(LoginController.class);

    private SearchPassportForm searchPassportForm = new SearchPassportForm();

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public String logIn(Model model) {
        model.addAttribute("searchPassportForm", searchPassportForm);
        model.addAttribute("customer", new Customer());

        return "signin";
    }

    @RequestMapping(value = "/processSignIn", method = RequestMethod.POST)
    public String signInPost(HttpSession session) {

        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        validateCustomer(authentication.getPrincipal());
        Customer customerLogged = ((UserDetailsImpl) authentication.getPrincipal()).getCust();

        session.setAttribute("customerID", customerLogged.getId());

        return "redirect:/home";
    }


    private void validateCustomer(Object customer) {
        if(!(customer instanceof UserDetailsImpl)) {
            throw new  IllegalArgumentException("Principal can not be null!");
        }
    }
}
