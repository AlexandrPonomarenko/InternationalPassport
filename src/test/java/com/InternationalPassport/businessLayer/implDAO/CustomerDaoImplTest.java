package com.InternationalPassport.businessLayer.implDAO;

import com.InternationalPassport.businessLayer.DAO.CustomerDAO;
import com.InternationalPassport.businessLayer.DAO.PassportDAO;
import com.InternationalPassport.businessLayer.model.Address;
import com.InternationalPassport.businessLayer.model.Customer;
import com.InternationalPassport.businessLayer.model.Passport;
import com.InternationalPassport.businessLayer.model.Role;
import com.InternationalPassport.springConfigs.BeensConfig;
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

import java.time.LocalDate;
import java.util.List;

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

    @Autowired
    PassportDAO passportDAO;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findByFirstName() {
        String expect = "Alex";
        String actual = "";
        List<Customer> customerList = customerDAO.findByFirstName(expect);
        for (Customer cl: customerList) {
            if(cl.getName().equals(expect)) {
                actual = cl.getName();
            }
        }
        logger.debug("findByFirstNameTest: ----- " + expect + "EXPECT " + actual + " Actual");
        assertNotNull(customerList);
        assertEquals(expect, actual);
    }

    @Test
    public void findByLogin() {
        String expect = "LOGIN2";
        String actual = "";
        Customer customer  = customerDAO.findByLogin("LOGIN2");
        actual = customer.getLogin();
        logger.debug("findByLoginTest: ----- " + expect + "EXPECT " + actual + " Actual");
        assertNotNull(customer);
        assertEquals(expect, actual);
    }

    @Test
    public void findByEmail() {
        String expect = "furriets@gmail.com";
        String actual = "";
        Customer customer  = customerDAO.findByEmail("furriets@gmail.com");
        actual = customer.getEmail();
        logger.debug("findByEmailTest : ----- " + expect + "EXPECT " + actual + " Actual");
        assertNotNull(customer);
        assertEquals(expect, actual);
    }

    @Test
    public void findById() {
        Integer expect = 7;
        Integer actual = null;
        Customer customer  = customerDAO.findById(7);
        actual = customer.getId();
        logger.debug("findByIdTest : ----- " + expect + "EXPECT " + actual + " Actual");
        assertNotNull(customer);
        assertEquals(expect, actual);
    }
//
//    @Test
//    public void findByQuery() {
//    }

    @Test
    public void findAll() {
        Integer expect = 3;
        Integer actual = 0;
        List<Customer> customers  = customerDAO.findAll();
        actual = customers.size();
        for (Customer customer: customers) {
            logger.debug("findAll: - " + customer.toString() + ": CUSTOMER");
        }
        assertNotNull(customers);
        assertEquals(expect, actual);
    }

    @Test
    public void persist() {
        String expected = "Sharl";
        String actual = "";
        List<Customer> actuals;
        Role roleOne = new Role("User");
        Address addressOne = new Address("Germany", "Berlin", "Burface Street", 11);
        Customer customerOne = new Customer("Sharl", "Tuff", "Guff", 54, LocalDate.of(1952, 3,20),
            "fgermany@gmail.com", "LOGIN14", "54rt67", roleOne);
        roleOne.getCustomers().add(customerOne);
        customerOne.setAddress(addressOne);
        addressOne.getCustomerList().add(customerOne);
        Passport passportOne = new Passport("qq77700", "foreign", customerOne);
        customerOne.setPassport(passportOne);

        customerDAO.persist(customerOne);
        actuals = customerDAO.findByFirstName(customerOne.getName());
        for(Customer ac: actuals) {
            if(ac.getName().equals(expected)) {
                logger.debug(actual + " Name DEBUG+++++++++++++++++++++=");
                actual = ac.getName();
            }

            logger.debug(actual + "persistTest Name DEBUG");

        }
        assertEquals(expected, actual);
        assertNotNull(actual);

    }

    @Test
    public void update() {
        String expect = "PopovUpdate";
        String actual = "";
        Customer customerUpdate = customerDAO.findById(9);
        actual = customerUpdate.getLastName();
        assertEquals("Popov", actual);
        customerUpdate.setLastName(expect);
        Customer customerActual = customerDAO.findById(9);
        logger.debug("updateTest : ----- " + expect + "EXPECT " + actual + " Actual");
        assertNotNull(customerActual);
        assertEquals(expect, customerActual.getLastName());
    }

    @Test
    public void delete() {
        List<Customer> customerList = null;
        Customer customerAct = customerDAO.findById(9);
        int actual = 3;
        int expectSize = 2;
        assertNotNull(customerAct);
        customerList = customerDAO.findAll();
        assertNotNull(customerList);
        assertEquals(customerList.size(), actual);
        customerDAO.delete(customerAct);
        customerList = customerDAO.findAll();
        logger.debug("updateTest : ----- " + expectSize + " expectSize " + customerList.size() + " customerList.size()");
        assertEquals(expectSize, customerList.size());
    }
}