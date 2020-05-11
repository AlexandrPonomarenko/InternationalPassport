package com.InternationalPassport.businessLayer.service.serviceImpl;

import com.InternationalPassport.businessLayer.model.Address;
import com.InternationalPassport.businessLayer.model.Customer;
import com.InternationalPassport.businessLayer.service.AddressService;
import com.InternationalPassport.businessLayer.service.CustomerService;
import com.InternationalPassport.springConfigs.SpringJPAConfigTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = SpringJPAConfigTest.class)
@Transactional
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class DataForManagerControllerTest {

    private final Logger logger = LogManager.getLogger(DataForManagerControllerTest.class);

    @Autowired
    CustomerService customerService;

    @Autowired
    AddressService addressService;

    @Test
    public void getAllCustomers() {
        List<Customer> customers = customerService.findAll();
        for (Customer customer: customers) {
            logger.debug(customer.getRole().getRole());
        }
        logger.debug(customers);
    }

    @Test
    public void getOne() {
        Customer customer = customerService.findByLogin("CusLOGIN");
//        Customer customer1 = customerService.findById(1);
        logger.debug("getOne" +  customer);
//        logger.debug("getOne role + " +  customer.getRole().getRole());
        assertNotNull(customer);
    }

    @Test
    public void getAddressByCustomerIdCaseNullTest() {
        Address address = addressService.findById(5);
        logger.debug("getAddressByCustomerIdCaseNullTest -- " +  address);

        assertNull(address);
    }
}
