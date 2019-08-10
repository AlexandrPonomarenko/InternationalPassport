package com.InternationalPassport.businessLayer.service.serviceImpl;

import com.InternationalPassport.businessLayer.model.Address;
import com.InternationalPassport.businessLayer.service.AddressService;
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

import java.util.List;

import static org.junit.Assert.*;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = SpringJPAConfigTest.class)
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
public class AddressServiceImplTest {

    private static final Logger logger = LogManager.getLogger(AddressServiceImplTest.class);

    @Autowired
    AddressService addressService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findById() {
        int expect = 7;
        int actual = 0;
        Address address = addressService.findById(expect);
        actual = address.getId();
        logger.debug("Test Service findById --- " + address);
        assertEquals(expect, actual);
    }

//    @Test
//    public void findByQuery() {
//    }

    @Test
    public void findAll() {
        int expect = 3;
        int actual = 0;
        List<Address> addresses = addressService.findAll();
        actual = addresses.size();
        logger.debug("Test Service findAll --- " + addresses);
        assertEquals(expect, actual);
    }

//    @Test
//    public void persist() {
//    }
//
//    @Test
//    public void update() {
//    }
//
//    @Test
//    public void delete() {
//    }
}