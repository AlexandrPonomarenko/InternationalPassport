package com.InternationalPassport.businessLayer.implDAO;

import com.InternationalPassport.businessLayer.DAO.CustomerDAO;
import com.InternationalPassport.businessLayer.model.Address;
import com.InternationalPassport.businessLayer.model.Customer;
import com.InternationalPassport.businessLayer.model.Passport;
import com.InternationalPassport.businessLayer.model.Role;
import com.InternationalPassport.springConfigs.BeensConfig;
import com.InternationalPassport.springConfigs.SpringJPAConfigTest;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.Assert.*;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD) //- ???
@ContextConfiguration(classes = {SpringJPAConfigTest.class, BeensConfig.class})
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerDaoImplTest {


    private static final Logger logger = LogManager.getLogger(CustomerDaoImplTest.class);


//    @Autowired
//    CustomerDaoImpl customerDaoImpl;
    @Autowired
    CustomerDAO customerDAO;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

//    @Test
//    public void findByFirstName() {
//    }
//
//    @Test
//    public void findByLogin() {
//    }
//
//    @Test
//    public void findByEmail() {
//    }
//
//    @Test
//    public void findById() {
//    }
//
//    @Test
//    public void findByQuery() {
//    }
//
//    @Test
//    public void findAll() {
//    }

    @Test
    public void persist() {
        String expected = "Alex";
        String actual = "";
        Role roleOne = new Role("User");
        Address addressOne = new Address("Ukraine", "Kharkiv", "Gagarina Street", 42);
        Customer customerOne = new Customer("Alex", "Anat", "Pon", 99, LocalDate.of(1992, 2,23),
            "furriets@gmail.com", "12345", roleOne);
//        roleOne.setCustomers(customerOne);
        roleOne.getCustomers().add(customerOne);
        customerOne.setAddress(addressOne);
        addressOne.setCustomerList(customerOne);
        Passport passportOne = new Passport("MM123GG", "native", customerOne);
        customerOne.setPassportList(passportOne);

        customerDAO.persist(customerOne);
        actual = customerDAO.findByFirstName(customerOne.getName()).getName();
        logger.debug(actual + " DEBUG");
        assertEquals(expected, actual);

    }

//    @Test
//    public void update() {
//
//    }
//
//    @Test
//    public void delete() {
//    }
}