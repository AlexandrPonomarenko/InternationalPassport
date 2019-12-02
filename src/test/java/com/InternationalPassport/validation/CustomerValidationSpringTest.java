package com.InternationalPassport.validation;

import com.InternationalPassport.businessLayer.model.Customer;
import com.InternationalPassport.springConfigs.SpringDataConfig;
import com.InternationalPassport.springConfigs.SpringJPAConfigTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.validation.DataBinder;

import javax.validation.ConstraintViolation;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Set;


//@ContextConfiguration(classes = SpringJPAConfigTest.class)
@ContextConfiguration(classes = SpringDataConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class CustomerValidationSpringTest {

    private final Logger logger = LogManager.getLogger(CustomerValidationSpringTest.class);
//    private static final ResourceBundleMessageSource RESOURCE_BUNDLE_MESSAGE_SOURCE  = new ResourceBundleMessageSource();
//    static {
//        RESOURCE_BUNDLE_MESSAGE_SOURCE.setBasename("validationMessages");
//    }

    @Autowired
    CustomerValidator customerValidator;

    @Before
    public void setUp() throws Exception {

    }

    @Before
    public void tearDown() throws Exception {

    }

//    @Test
//    public void checkCustomerSpringTest() {
//        Customer customer = new Customer("Alex", "Anatoliovich", "Po",
//                -4599, LocalDate.of(1992, 2, 23), "qwer", "login", "ttt");
//        logger.debug(customer.toStringLogin());
//        final DataBinder dataBinder = new DataBinder(customer);
//        dataBinder.addValidators(customerValidator);
//        dataBinder.validate();
//
//        Assert.assertTrue(dataBinder.getBindingResult().hasErrors());
//
////        if (dataBinder.getBindingResult().hasErrors()) {
////            dataBinder.getBindingResult().getAllErrors().stream()
////                    .forEach(e -> System.out.println(RESOURCE_BUNDLE_MESSAGE_SOURCE.getMessage(e, Locale.getDefault())));
////        }
//    }


    @Test
    public void TestCustomer() {
        Customer customer = new Customer("Alex", "Anatoliovich", "Po",
                -4599, LocalDate.of(1992, 2, 23), "qwer", "login", "ttt");


    }
}
