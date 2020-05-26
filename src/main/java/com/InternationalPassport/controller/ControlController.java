package com.InternationalPassport.controller;

import com.InternationalPassport.businessLayer.model.Customer;
import com.InternationalPassport.businessLayer.service.CustomerService;
import com.InternationalPassport.helper.CustomerTransferModel;
import com.InternationalPassport.helper.SearchPassportForm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;

@Controller(value = "ControlController")
public class ControlController {
    public static final Logger logger = LogManager.getLogger(ControlController.class);

    @Autowired
    private CustomerService customerService;

    private SearchPassportForm searchPassportForm = new SearchPassportForm();
    private CustomerTransferModel customerTransferModel;
    private CustomerTransferModel customerTest = new CustomerTransferModel("CusLOGIN1", false);

    @RequestMapping(value = "/control", method = RequestMethod.GET)
    public String getControlPage(Model model) {
        model.addAttribute("searchPassportForm", searchPassportForm);
        model.addAttribute("customers", prepareCustomer());
        return "control";
    }

    @RequestMapping(value = "/control", method = RequestMethod.POST)
    @ResponseBody
    public CustomerTransferModel blockCustomer (@RequestBody CustomerTransferModel ctm/*, Model model, HttpServletRequest request*/) {
        logger.debug("customer for block ");
        logger.debug("CUSSSSSSSSS " + ctm.toString());
//        model.addAttribute("searchPassportForm", searchPassportForm);
//        model.addAttribute("customers", prepareCustomer());

//        blockUser(ctm);
        buildResponseObject(blockUser(ctm));
//        return customerTest;
        return customerTransferModel;
    }

    private List<Customer> prepareCustomer() {
        List<Customer> customers = customerService.findByRole("User");
        logger.debug("FIND BY ROLE YO " + customers);
        return  customers;
    }

    private Customer blockUser(CustomerTransferModel ctm) {
        Customer customer = customerService.findByLogin(ctm.getLogin());
        customer.setStatusAccount(!ctm.isStatusAccount());
        customer.setRepeatPassword("qweasdzxcrtyDSAv231xzX"); // with this need something to do!! here error!
        logger.debug("BLOCK USER -- " + customer);
        customerService.update(customer);
        return customer;
    }

    private void buildResponseObject(Customer customer) {
        customerTransferModel = new CustomerTransferModel();
        customerTransferModel.setLogin(customer.getLogin());
        customerTransferModel.setStatusAccount(customer.isStatusAccount());
    }
}
