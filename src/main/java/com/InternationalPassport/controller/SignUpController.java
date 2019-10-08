package com.InternationalPassport.controller;

import com.InternationalPassport.businessLayer.model.Customer;
import com.InternationalPassport.helper.SearchPassportForm;
//import com.sun.org.apache.xpath.internal.operations.String;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Controller("SignUpController")
public class SignUpController {

    private final Logger logger = LogManager.getLogger(SignUpController.class);

    private SearchPassportForm searchPassportForm = new SearchPassportForm();

//    @Autowired
//    private CustomerService customerService;

//    @InitBinder
//    public void initBinder(WebDataBinder binder) {
////        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
//        SimpleDateFormat sdf = new SimpleDateFormat();
//        sdf.setLenient(true);
//        binder.registerCustomEditor(LocalDate.class, "birthDate", new CustomDateEditor(sdf, true));
//    }

    @RequestMapping(value = "/signUp", method = RequestMethod.GET)
    public String signUp(Model model) {
        model.addAttribute("searchPassportForm", searchPassportForm);
        model.addAttribute("customer", new Customer());
        return "signUp";
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public void regCustomer(@ModelAttribute Customer customer, SearchPassportForm searchPassportForm) {
        Customer cus = customer;
//        logger.debug("JUST DATA " + localDate.toString());
        logger.debug("SIGH-UP login customer " +  cus.toStringLogin());
        logger.debug("SIGH-UP login searchPassportForm " +  searchPassportForm.toString());
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
