package com.InternationalPassport.businessLayer.implDAO;

import com.InternationalPassport.businessLayer.DAO.AddressDAO;
import com.InternationalPassport.businessLayer.DAO.CustomerDAO;
import com.InternationalPassport.businessLayer.model.Address;
import com.InternationalPassport.businessLayer.model.Customer;
import com.InternationalPassport.springConfigs.SpringJPAConfigTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
@ContextConfiguration(classes = SpringJPAConfigTest.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class AddressDaoImplTest {

    private static final Logger logger = LogManager.getLogger(AddressDaoImplTest.class);

    @Autowired
    AddressDAO addressDAO;

    @Autowired
    CustomerDAO customerDAO;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void findById() {
        int expect = 7;
        Address address = addressDAO.findById(7);
        int actual = address.getId();
        assertEquals(expect, actual);
        logger.debug("test findById  - " + address);
    }

//    @Test
//    public void findByQuery() {
//    }

    @Test
    public void findAll() {
        int expect = 3;
        List<Address> addresses = addressDAO.findAll();
        int actual = addresses.size();
        assertEquals(expect, actual);
        logger.debug("test findAll -- " + addresses.size());
    }

    @Test
    public void persist() {
        int expect = 4;
        int actual = 0;
        Address a = new Address("Poland", "Krakov", "Trum st.", 54);
        addressDAO.persist(a);
        actual = addressDAO.findAll().size();
        assertEquals(expect, actual);
        logger.debug("test persist -- " + actual);
    }

    @Test
    public void update() {
        String expectNameCity = "Kiuv";
        Address address = addressDAO.findById(8);
        String actualNameCity = address.getCity();
        logger.debug("get old name city entity  == " + address);
        address.setCity(expectNameCity);
        addressDAO.update(address);
        actualNameCity = addressDAO.findById(8).getCity();
        assertEquals(expectNameCity, actualNameCity);
        logger.debug( "test update -- " + actualNameCity);
    }

    @Test
    public void delete() {
        Address expectAddress = null;
        Address actualAddress = addressDAO.findById(8);
        assertNotNull(actualAddress);
        Customer customer = customerDAO.findById(7);
        int actualIdAddress = customer.getAddress().getId();
        customerDAO.delete(customer);
        List<Address> addresses = addressDAO.findAll();
        logger.debug("SIZE LIST ADDRESSES -- " + addresses.size());
        actualAddress = addressDAO.findById(actualIdAddress);

        assertEquals(expectAddress, actualAddress);
        logger.debug("test delete -- " + actualAddress);
    }
}