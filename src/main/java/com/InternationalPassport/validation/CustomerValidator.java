package com.InternationalPassport.validation;

import com.InternationalPassport.businessLayer.model.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;


//TODO use both validation spring and java

@Service
public class CustomerValidator implements org.springframework.validation.Validator {

    private static final Logger logger = LogManager.getLogger(CustomerValidator.class);

    @Autowired
    private javax.validation.Validator validator;

    @Override
    public boolean supports(Class<?> aClass) {
        return Customer.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Set<ConstraintViolation<Object>> validates = validator.validate(o);

        for (ConstraintViolation<Object> constraintViolation: validates) {
            String propertyPath = constraintViolation.getPropertyPath().toString();
            String message = constraintViolation.getMessage();
            logger.debug(propertyPath + " :: propertyPath");
            logger.debug(message + " :: message");
            errors.rejectValue(propertyPath, "", message);
        }

        Customer customer = (Customer)o;
        if (customer.getAge() == null) {
            logger.debug("IF block");
            errors.rejectValue("age", "value.null");
        } else if (customer.getAge() < 0) {
            logger.debug("else block");
            errors.rejectValue("age", "value.negative");
        }

        if(customer.getEmail() == null) {
            errors.rejectValue("email", "email.notempty");
        }
//        for (ObjectError objectError: errors.getAllErrors()) {
//            System.out.println("1" + objectError.toString());
////            System.out.println("2" + objectError.getObjectName());
//        }


//        logger.debug(customer.getBirthDate().getClass() + " :: class");
    }
}
