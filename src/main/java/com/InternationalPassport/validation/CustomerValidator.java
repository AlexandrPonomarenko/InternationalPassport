package com.InternationalPassport.validation;

import com.InternationalPassport.businessLayer.model.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;


@Service
public class CustomerValidator implements org.springframework.validation.Validator {

    private static final Logger logger = LogManager.getLogger(CustomerValidator.class);

    @Override
    public boolean supports(Class<?> aClass) {
        return Customer.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "name", "customer.name.NotNull");
        ValidationUtils.rejectIfEmpty(errors, "patronymic", "customer.patronymic.NotNull");
        ValidationUtils.rejectIfEmpty(errors, "lastName", "customer.lastName.NotNull");
        ValidationUtils.rejectIfEmpty(errors, "birthDate", "customer.birthDate.NotNull");
        ValidationUtils.rejectIfEmpty(errors, "email", "customer.email.NotNull");
        ValidationUtils.rejectIfEmpty(errors, "login", "customer.login.NotNull");
        ValidationUtils.rejectIfEmpty(errors, "password", "customer.password.NotNull");
        ValidationUtils.rejectIfEmpty(errors, "repeatPassword", "customer.repeatPassword.NotNull");

        Customer customer = (Customer) o;
        if (customer.getEmail().length() < 10) {
            errors.rejectValue("email", "customer.email.Email");
        }

        if (customer.getAge() == null) {
            errors.rejectValue("age", "customer.age.NotNull");
        }

        if (customer.getAge() != null && (customer.getAge() < 0 || customer.getAge() > 130)) {
            errors.rejectValue("age", "customer.age.WrongAge");
        }

        if (!(customer.getPassword().equals(customer.getRepeatPassword()))) {
            errors.rejectValue("repeatPassword", "customer.repeatPassword.same");
        }
    }
}
