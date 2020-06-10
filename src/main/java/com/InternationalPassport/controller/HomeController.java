package com.InternationalPassport.controller;

import com.InternationalPassport.businessLayer.model.Address;
import com.InternationalPassport.businessLayer.model.Customer;
import com.InternationalPassport.businessLayer.model.Passport;
import com.InternationalPassport.businessLayer.service.AddressService;
import com.InternationalPassport.businessLayer.service.CustomerService;
import com.InternationalPassport.businessLayer.service.PassportService;
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

    @Autowired
    private AddressService addressService;

    @Autowired
    private PassportService passportService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Model model, HttpSession httpSession) {
//        model.addAttribute("searchPassportForm", searchPassportForm);
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

        authCustomer = getAuthCustomer();

        model.addAttribute("newCustomerData", new Customer());
        model.addAttribute("customer", authCustomer);
        model.addAttribute("address", authCustomer.getAddress());
        model.addAttribute("passport", authCustomer.getPassport());
        model.addAttribute("photo", authCustomer.getPhotos().get(0));

        logger.debug("PHOTO YO !!!!!!!!!!! --- > " + authCustomer.getPhotos().get(0));

        if (authCustomer.getAddress() == null) {
            model.addAttribute("address", new Address());
        }

        if (authCustomer.getPassport() == null) {
            model.addAttribute("passport", new Passport());
        }
        model.addAttribute("newAddressData", new Address());
        model.addAttribute("newPassportData", new Passport());
        return "home";
    }

    @RequestMapping(value = "/changeData", method = RequestMethod.POST)
    public String changeName(@ModelAttribute(name = "newCustomerData") Customer newCustomerData, Model model) {
        logger.debug("change name  REDIRECT -- POST -- newCustomerData" + newCustomerData.toStringLogin());
//        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
//        Customer authCustomer = ((UserDetailsImpl) authentication.getPrincipal()).getCust();

//        logger.debug("ADDRES -- " + authCustomer.getAddress());

//        model.addAttribute("customer", authCustomer);
//        model.addAttribute("newCustomerData", new Customer());
//        model.addAttribute("address", authCustomer.getAddress());
//        model.addAttribute("newAddressData", new Address());
//        model.addAttribute("passport", authCustomer.getPassport());
//        model.addAttribute("newPassportData", new Passport());
//        return "redirect:home";
//        return "forward:home";
        return "home";
    }

    @RequestMapping(value = "/addressData", method = RequestMethod.POST)
    public String changeAddressData(@ModelAttribute(name = "newAddressData") Address newAddressData, Model model) {
        logger.debug("PROCCESS DATA ADDRESS " + newAddressData.toString());

        updateAddressCustomer(newAddressData);

        return "redirect:home";
    }

    @RequestMapping(value = "/passportData", method = RequestMethod.POST)
    public String changePassportData(@ModelAttribute(name = "newPassportData") Passport newPassportData, Model model) {

        logger.debug("PROCCESS DATA PASSPORT " + newPassportData.toString());

        updatePassportCustomer(newPassportData);

        return "redirect:home";
    }



    private Customer getAuthCustomer() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Customer customer = customerService.findByIdInitAll((((UserDetailsImpl) authentication.getPrincipal()).getCust()).getId());
        return customer;
    }

    private void updateAddressCustomer(Address address) {
        if (address == null) { return; }
        Customer customer = customerService.findByIdInitAll(getAuthCustomer().getId());
        Address findAddress = null;
        if (customer.getAddress() != null) {
//            findAddress = addressService.findById(customer.getAddress().getId());
            findAddress = customer.getAddress();
            findAddress.setCity(address.getCity());
            findAddress.setStreet(address.getStreet());
            findAddress.setNumberHome(address.getNumberHome());
        } else {
            customer.setAddress(address);
            address.getCustomerList().add(customer);
        }
        customerService.update(customer);
    }

    private void updatePassportCustomer(Passport passport) {
        if (passport == null) { return; }
        Customer customer = customerService.findByIdInitAll(getAuthCustomer().getId());
        logger.debug("PASSPORT -- > " + customer.getPassport());

        if (customer.getPassport() != null) {
            Passport findPassport = customer.getPassport();
            findPassport.setSeria(passport.getSeria());
            findPassport.setType(passport.getType());
        } else {
            customer.setPassport(passport);
            passport.setCustomer(customer);
        }
        customerService.update(customer);

    }

//    private void updatePersonalData(Customer customer) {
//        if (customer == null) { return; }
//        Customer customer = customerService.findByIdInitAll(getAuthCustomer().getId());
//        logger.debug("PASSPORT -- > " + customer.getPassport());
//
//        if (customer.getPassport() != null) {
//            Passport findPassport = customer.getPassport();
//            findPassport.setSeria(passport.getSeria());
//            findPassport.setType(passport.getType());
//        } else {
//            customer.setPassport(passport);
//            passport.setCustomer(customer);
//        }
//        customerService.update(customer);
//
//    }


}
