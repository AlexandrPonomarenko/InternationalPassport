package com.InternationalPassport.validation;

import com.InternationalPassport.businessLayer.model.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.time.LocalDate;
import java.util.Set;


import static org.junit.Assert.*;

public class CustomerValidationTest {

    private static Validator validator;
    private final Logger logger = LogManager.getLogger(CustomerValidationTest.class);

    static {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.usingContext().getValidator();
    }

    @Before
    public void setUp() throws Exception {

    }

    @Before
    public void tearDown() throws Exception {

    }

    @Test
    public void checkCustomerTest() {
        Customer customer = new Customer("Alex", "Anatoliovich", "Ponomarenko",
                -4599, LocalDate.of(1992, 2, 23), "qwer", "login", "ttt");
        logger.debug(customer.toStringLogin());
        Set<ConstraintViolation<Customer>> validates = validator.validate(customer);
        Assert.assertTrue(validates.size() > 0);
        validates.stream().map(v -> v.getMessage()).forEach(System.out:: println);
    }
}
