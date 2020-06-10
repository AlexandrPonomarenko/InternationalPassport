package com.InternationalPassport.controller;

import com.InternationalPassport.businessLayer.model.Customer;
import com.InternationalPassport.businessLayer.model.Role;
import com.InternationalPassport.businessLayer.service.CustomerService;
import com.InternationalPassport.businessLayer.service.RoleService;
import com.InternationalPassport.helper.CustomerTransferModel;
import com.InternationalPassport.helper.RemoveCustomerForm;
import com.InternationalPassport.helper.SearchPassportForm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.model.IModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller(value = "CEOController")
@ControllerAdvice
public class CEOController {
    public static final Logger logger = LogManager.getLogger(CEOController.class);

    @Autowired
    private CustomerService customerService;

    @Autowired
    private RoleService roleService;

    private SearchPassportForm searchPassportForm = new SearchPassportForm();

    @ModelAttribute
    private void addSearchPasswordForm(Model model) {
        model.addAttribute(searchPassportForm);
    }

    @RequestMapping(value = "/ceo", method = RequestMethod.GET)
    public String getControlPage(Model model) {

        model.addAttribute("customers", prepareCustomer());
        model.addAttribute("roles", prepareRoles());
        model.addAttribute("sRole", new Role());
        return "ceo";
    }

    @RequestMapping(value = "/ceo", method = RequestMethod.POST)
    public String postCeo(@ModelAttribute("rcf") RemoveCustomerForm rcf, Model model) {

        removeCustomer(rcf);
        model.addAttribute("customers", prepareCustomer());
        model.addAttribute("roles", prepareRoles());
        model.addAttribute("sRole", new Role());

        return "ceo";
    }

    @RequestMapping(value = "/ceo2", method = RequestMethod.POST)
    public String postCeoTwo(@ModelAttribute("ctm") CustomerTransferModel ctm, Model model) {

        blockUser(ctm);

        model.addAttribute("customers", prepareCustomer());
        model.addAttribute("roles", prepareRoles());
        model.addAttribute("sRole", new Role());

        return "redirect:/ceo";
    }

    @RequestMapping(value = "/ceorole", method = RequestMethod.POST)
    public String postCeoRole(@ModelAttribute("role") Role role, @ModelAttribute("ctm") CustomerTransferModel ctm, Model model) {

        setNewRole(role, ctm);
        model.addAttribute("customers", prepareCustomer());
        model.addAttribute("roles", prepareRoles());
        model.addAttribute("sRole", new Role());

        return "redirect:/ceo";
    }

    private List<Customer> prepareCustomer() {
        List<Customer> customers = customerService.findAll();
        return  customers;
    }

    private void blockUser(CustomerTransferModel ctm) {
        Customer customer = customerService.findByLogin(ctm.getLogin());

        if (customer != null) {
            customer.setStatusAccount(!customer.isStatusAccount());
            customerService.update(customer);
        }
    }

    private void removeCustomer(RemoveCustomerForm rcf) {
        Customer customer;
        if (rcf.getLogin() != null) {
            customer = customerService.findByLogin(rcf.getLogin());
            customerService.delete(customer);
        }
    }

    private void setNewRole(Role role, CustomerTransferModel ctm) {
        Customer customer =  customerService.findByLogin(ctm.getLogin());
        Role updatedRole = roleService.findById(customer.getId());
        updatedRole.setRole(role.getRole());
        customer.setRole(updatedRole);

        customerService.update(customer);
    }

    private List<Role> prepareRoles() {
        List<Role> roles = roleService.findAll();
        ArrayList<Role> resultRoles = new ArrayList<>();

        roles.forEach((Role role) -> {
            if (role.getRole().equals("User") && !checkByNameRole(role.getRole(), resultRoles)) {
                resultRoles.add(role);
            } else if (role.getRole().equals("Manager") && !checkByNameRole(role.getRole(), resultRoles)) {
                resultRoles.add(role);
            } else if (role.getRole().equals("CEO") && !checkByNameRole(role.getRole(), resultRoles)) {
                resultRoles.add(role);
            }
        });

        return resultRoles;
    }

    private boolean checkByNameRole(String name, ArrayList<Role> roles) {
        boolean result = false;
        if (roles.size() > 0) {
            for (Role roleItem: roles) {
                if (roleItem.getRole().equals(name)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
}
