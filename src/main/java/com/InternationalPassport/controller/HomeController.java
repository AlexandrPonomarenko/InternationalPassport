package com.InternationalPassport.controller;

import com.InternationalPassport.businessLayer.model.Customer;
import com.InternationalPassport.businessLayer.service.CustomerService;
import com.InternationalPassport.helper.SearchPassportForm;
import com.InternationalPassport.security.UserDetailsImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {
    private static final Logger logger = LogManager.getLogger(HomeController.class);

    private SearchPassportForm searchPassportForm = new SearchPassportForm();

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Model model, HttpSession httpSession) {
        model.addAttribute("searchPassportForm", searchPassportForm);
        logger.debug("FROM HOME");
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        System.out.println("authentication getPrincipal get role ----->>>> " + ((UserDetailsImpl) authentication.getPrincipal()).getCust().getPassword());
        Customer authCustomer = ((UserDetailsImpl) authentication.getPrincipal()).getCust();
//        System.out.println("authentication isAuthenticated----->>>> " + authentication.isAuthenticated());
        System.out.println("GET NAME "  +  ((UserDetailsImpl) authentication.getPrincipal()).getUsername());
        System.out.println("authentication getCredentials ----->>>> " + (String) SecurityContextHolder.getContext().getAuthentication().getCredentials());
        System.out.println(" httpSession.getId() ----->>>> " + httpSession.getId());
        System.out.println(" httpSession. RESULTTTTTTTT () ----->>>> " + httpSession.getAttribute("BRO"));
        System.out.println("SPRING_SECURITY_CONTEXT ----->>>> " + httpSession.getAttribute("SPRING_SECURITY_CONTEXT"));
        SecurityContext crt = (SecurityContext) httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
//        UsernamePasswordAuthenticationToken upat = (UsernamePasswordAuthenticationToken) crt.getAuthentication().getDetails();
        System.out.println(" CUS DUESD  ----->>>> " + ((UserDetailsImpl) crt.getAuthentication().getPrincipal()).getCust());


        Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
        Object objCus = auth.getPrincipal();
        if (objCus instanceof UserDetailsImpl) {
            UserDetailsImpl c = (UserDetailsImpl) objCus;
            System.out.println(auth.getName() + " ::: AAAAAAAAAAAAAAA EEEEEEEEEEEEEEEEEEEEE WWWWWWWWWWWWWWWWWw" + c);
        }

        model.addAttribute("customer", getBlockedCustomer(authCustomer.getLogin()));
        return "home";
    }

//    @RequestMapping(value = "/home", method = RequestMethod.POST)
//    public String postHome( Model model) {
//        logger.debug("POSTTTTTTTTTTTTTTTT" );
//
//        model.addAttribute("searchPassportForm", searchPassportForm);
////        model.addAttribute("customer", getBlockedCustomer(authCustomer.getLogin()));
//
//        return "home";
//    }

    @RequestMapping(value = "/changeData", method = RequestMethod.POST)
    public String changeName(@ModelAttribute(name = "c") Customer c, Model model) {
        logger.debug("change name  REDIRECT -- POST -- c" + c.toStringLogin());
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        Customer authCustomer = ((UserDetailsImpl) authentication.getPrincipal()).getCust();

//        model.addAttribute("searchPassportForm", searchPassportForm);
//        model.addAttribute("customer", getBlockedCustomer(authCustomer.getLogin()));
        return "redirect:home";
//        return "forward:home";
//        return "home";
    }

    private Customer getBlockedCustomer(String login) {
        return customerService.findByLogin(login);
    }

}
