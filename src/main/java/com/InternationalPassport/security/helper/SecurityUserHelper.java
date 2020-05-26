package com.InternationalPassport.security.helper;

import com.InternationalPassport.businessLayer.model.Customer;
import com.InternationalPassport.security.UserDetailsImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUserHelper {
    private Customer customer;
    private UserDetailsImpl userDetails;
    private final Logger logger = LogManager.getLogger(SecurityUserHelper.class);

    public SecurityUserHelper() {

    }

    public void show() {
        logger.debug("============================= " + ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getCust());
    }

}
