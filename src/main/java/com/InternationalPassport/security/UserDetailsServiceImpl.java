package com.InternationalPassport.security;

import com.InternationalPassport.businessLayer.model.Customer;
import com.InternationalPassport.businessLayer.model.Role;
import com.InternationalPassport.businessLayer.service.RoleService;
import com.InternationalPassport.businessLayer.service.serviceImpl.RoleServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    public static final Logger logger = LogManager.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    CustomerSecurityRep customerSecurityRep;

    @Autowired
    RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String userLogin) throws UsernameNotFoundException {
        logger.debug( "DEBUG FROM loadUserByUsername " + userLogin + " ::::");
        Customer customer = customerSecurityRep.findByLogin(userLogin);

        if (customer == null) {
            throw new UsernameNotFoundException("User not found.");
        }

        logger.debug("CUSTOMER FROM UserDetailsServiceImpl -- " + customer.getLogin() + "and password " + customer.getPassword());
        return new UserDetailsImpl(customer, getRoles());
    }

    private List<Role> getRoles() {
        return roleService.findAll();
    }
}
