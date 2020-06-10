package com.InternationalPassport.businessLayer.implDAO;

import com.InternationalPassport.businessLayer.DAO.CustomerDAO;
import com.InternationalPassport.businessLayer.model.Address;
import com.InternationalPassport.businessLayer.model.Customer;
import com.InternationalPassport.businessLayer.model.Passport;
import com.InternationalPassport.businessLayer.model.Role;
import com.InternationalPassport.springConfigs.BeensConfig;
import com.InternationalPassport.springConfigs.SpringJPAConfigTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD) //- ???
@ContextConfiguration(classes = {SpringJPAConfigTest.class, BeensConfig.class})
@Transactional
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class InitEntityTest {
    private static final Logger logger = LogManager.getLogger(InitEntityTest.class);



    @Autowired
    CustomerDAO customerDAO;

    @Rollback(false)
    @Test
    public void persist() {
        String expected = "CustomerName1";
        String actual = "";
        List<Customer> actuals;
        Role roleOne = new Role("User");
        Address addressOne = new Address("Ukraine", "Kharkiv", "Gagarina Street", 42);
        Customer customerOne = new Customer("CustomerName1", "CusMidName1",
                "CusLasName1", 99, LocalDate.of(1992, 2,23),
                "cusEmail1@gmail.com", "CusLOGIN1", "12345qwert", "12345qwert",  roleOne);
        roleOne.getCustomers().add(customerOne);
        customerOne.setAddress(addressOne);
        addressOne.getCustomerList().add(customerOne);
        Passport passportOne = new Passport("MM123GG", "native", customerOne);
        customerOne.setPassport(passportOne);

        customerDAO.persist(customerOne);
        actuals = customerDAO.findByFirstName(customerOne.getName());
        for(Customer ac: actuals) {
            if(ac.getName().equals(expected)) {
                actual = ac.getName();
            }

            logger.debug(actual + " Name DEBUG");
        }
        assertEquals(expected, actual);
        assertNotNull(actual);

    }

   @Rollback(false)
    @Test
    public void persistTwo() {
        String expected = "CustomerName2";
        String actual = "";
        List<Customer> actuals;
        Role roleOne = new Role("Manager");
        Address addressOne = new Address("Poland", "Krakov", "Unit Street", 100);
        Customer customerOne = new Customer("CustomerName2", "CusMidName2", "CusLastName2", 77, LocalDate.of(1982, 4,12),
                "cusEmail2@gmail.com", "cusLOGIN2", "12345qwert", "12345qwert", roleOne);
        roleOne.getCustomers().add(customerOne);
        customerOne.setAddress(addressOne);
        addressOne.getCustomerList().add(customerOne);
        Passport passportOne = new Passport("GG321BB", "native", customerOne);
        customerOne.setPassport(passportOne);

        customerDAO.persist(customerOne);
        actuals = customerDAO.findByFirstName(customerOne.getName());
        for(Customer ac: actuals) {
            if(ac.getName().equals(expected)) {
                actual = ac.getName();
            }

            logger.debug(actual + " Name DEBUG");
        }
        assertEquals(expected, actual);
        assertNotNull(actual);

    }

    @Rollback(false)
    @Test
    public void persistThree() {
        String expected = "CustomerName3";
        String actual = "";
        List<Customer> actuals;
        Role roleOne = new Role("CEO");
        Address addressOne = new Address("USA", "NewYork", "BroTroy Street", 55);
        Customer customerOne = new Customer("CustomerName3", "CusMidName3", "CusMLasName3", 44, LocalDate.of(1972, 7,30),
                "cusEmail3@gmail.com", "cusLOGIN3", "12345qwert", "12345qwert", roleOne);
        roleOne.getCustomers().add(customerOne);
        customerOne.setAddress(addressOne);
        addressOne.getCustomerList().add(customerOne);
        Passport passportOne = new Passport("AA777MM", "foregin", customerOne);
        customerOne.setPassport(passportOne);

        customerDAO.persist(customerOne);
        actuals = customerDAO.findByFirstName(customerOne.getName());
        for(Customer ac: actuals) {
            if(ac.getName().equals(expected)) {
                actual = ac.getName();
            }

            logger.debug(actual + " Name DEBUG");
        }
        assertEquals(expected, actual);
        assertNotNull(actual);
    }
}
