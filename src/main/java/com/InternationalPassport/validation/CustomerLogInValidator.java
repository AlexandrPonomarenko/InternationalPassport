package com.InternationalPassport.validation;

import com.InternationalPassport.businessLayer.model.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

@Service
public class CustomerLogInValidator implements org.springframework.validation.Validator {
    private static final Logger logger = LogManager.getLogger(CustomerLogInValidator.class);

    @Override
    public boolean supports(Class<?> aClass) {
        return Customer.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "login", "customer.login.NotNull");
        ValidationUtils.rejectIfEmpty(errors, "password", "customer.password.NotNull");
    }
}
