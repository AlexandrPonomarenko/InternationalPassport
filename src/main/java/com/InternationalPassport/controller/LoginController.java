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

//    @Autowired
//    private CustomerLogInValidator customerLogInValidator;

    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public String logIn(Model model) {
        model.addAttribute("searchPassportForm", searchPassportForm);
        model.addAttribute("customer", new Customer());
        logger.debug("METHODDDDDDD GETTTTTTTTTTTTTTTTTTT");
        return "signin";
    }

//    @RequestMapping(value = "/singin", method = RequestMethod.POST)
//    public String logInPost(@ModelAttribute Customer customer, SearchPassportForm searchPassportForm,
//                            Model model, HttpSession session) {
//        logger.debug("login customer " +  customer.toStringLogin());
//        logger.debug("login searchPassportForm " +  searchPassportForm.toString());
//        logger.info("INFOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO -->>>>>>>>>>>>>>>>");
//        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
//        System.out.println("authentication ----->>>> " + authentication.getName());
//        validateCustomer(authentication.getPrincipal());
//        Customer customerLogged = ((UserDetailsImpl) authentication.getPrincipal()).getCust();
//        logger.debug("login SECURITY ------> " +  customerLogged.toStringLogin());
//
//        session.setAttribute("customerID", customerLogged.getId());
//
////        if (!IsLogin(customer)) {
////            model.addAttribute("loginError", true);
////            model.addAttribute("searchPassportForm", searchPassportForm);
////            return "logIn";
////        }
//
//        return "redirect:/home";
//    }

    @RequestMapping(value = "/processSignIn", method = RequestMethod.POST)
    public String signInPost(/*@ModelAttribute Customer customer, SearchPassportForm searchPassportForm,
                            Model model,*/ HttpSession session) {
//        logger.debug("login customer " +  customer.toStringLogin());
//        logger.debug("login searchPassportForm " +  searchPassportForm.toString());
        logger.info("INFOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO -->>>>>>>>>>>>>>>>");
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        System.out.println("authentication ----->>>> " + authentication.getName());
        validateCustomer(authentication.getPrincipal());
        Customer customerLogged = ((UserDetailsImpl) authentication.getPrincipal()).getCust();
        logger.debug("login SECURITY ------> " +  customerLogged.toStringLogin());

        session.setAttribute("customerID", customerLogged.getId());

        return "redirect:/home";
    }


    private void validateCustomer(Object customer) {
        if(!(customer instanceof UserDetailsImpl)) {
            throw new  IllegalArgumentException("Principal can not be null!");
        }
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
