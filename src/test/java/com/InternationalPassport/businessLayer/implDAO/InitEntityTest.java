package com.InternationalPassport.businessLayer.implDAO;

import com.InternationalPassport.businessLayer.DAO.CustomerDAO;
import com.InternationalPassport.businessLayer.model.Address;
import com.InternationalPassport.businessLayer.model.Customer;
import com.InternationalPassport.businessLayer.model.Passport;
import com.InternationalPassport.businessLayer.model.Role;
import com.InternationalPassport.springConfigs.BeensConfig;
import com.InternationalPassport.springConfigs.SpringJPAConfigTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD) //- ???
@ContextConfiguration(classes = {SpringJPAConfigTest.class, BeensConfig.class})
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
public class InitEntityTest {
//    private static final Logger logger = LogManager.getLogger(CustomerDaoImplTest.class);

    @Autowired
    CustomerDAO customerDAO;

//    @Rollback(false)
//    @Test
//    public void persist() {
//        String expected = "Alex";
//        String actual = "";
//        List<Customer> actuals;
//        Role roleOne = new Role("User");
//        Address addressOne = new Address("Ukraine", "Kharkiv", "Gagarina Street", 42);
//        Customer customerOne = new Customer("Alex", "Anat", "Pon", 99, LocalDate.of(1992, 2,23),
//                "furriets@gmail.com", "LOGIN1", "12345", roleOne);
//        roleOne.getCustomers().add(customerOne);
//        customerOne.setAddress(addressOne);
//        addressOne.getCustomerList().add(customerOne);
//        Passport passportOne = new Passport("MM123GG", "native", customerOne);
//        customerOne.setPassport(passportOne);
//
//        customerDAO.persist(customerOne);
//        actuals = customerDAO.findByFirstName(customerOne.getName());
//        for(Customer ac: actuals) {
//            if(ac.getName().equals(expected)) {
//                actual = ac.getName();
//            }
//
//            logger.debug(actual + " Name DEBUG");
//        }
//        assertEquals(expected, actual);
//        assertNotNull(actual);
//
//    }
//
//   @Rollback(false)
//    @Test
//    public void persistTwo() {
//        String expected = "Petr";
//        String actual = "";
//        List<Customer> actuals;
//        Role roleOne = new Role("Manager");
//        Address addressOne = new Address("Poland", "Krakov", "Unit Street", 100);
//        Customer customerOne = new Customer("Petr", "Vas", "Popov", 77, LocalDate.of(1982, 4,12),
//                "popovich@gmail.com", "LOGIN2", "54321", roleOne);
//        roleOne.getCustomers().add(customerOne);
//        customerOne.setAddress(addressOne);
//        addressOne.getCustomerList().add(customerOne);
//        Passport passportOne = new Passport("GG321BB", "native", customerOne);
//        customerOne.setPassport(passportOne);
//
//        customerDAO.persist(customerOne);
//        actuals = customerDAO.findByFirstName(customerOne.getName());
//        for(Customer ac: actuals) {
//            if(ac.getName().equals(expected)) {
//                actual = ac.getName();
//            }
//
//            logger.debug(actual + " Name DEBUG");
//        }
//        assertEquals(expected, actual);
//        assertNotNull(actual);
//
//    }
//
//    @Rollback(false)
//    @Test
//    public void persistThree() {
//        String expected = "Joy";
//        String actual = "";
//        List<Customer> actuals;
//        Role roleOne = new Role("CEO");
//        Address addressOne = new Address("USA", "NewYork", "BroTroy Street", 55);
//        Customer customerOne = new Customer("Joy", "James", "Steelroy", 44, LocalDate.of(1972, 7,30),
//                "usaA@gmail.com", "LOGIN3", "444555222", roleOne);
//        roleOne.getCustomers().add(customerOne);
//        customerOne.setAddress(addressOne);
//        addressOne.getCustomerList().add(customerOne);
//        Passport passportOne = new Passport("AA777MM", "foregin", customerOne);
//        customerOne.setPassport(passportOne);
//
//        customerDAO.persist(customerOne);
//        actuals = customerDAO.findByFirstName(customerOne.getName());
//        for(Customer ac: actuals) {
//            if(ac.getName().equals(expected)) {
//                actual = ac.getName();
//            }
//
//            logger.debug(actual + " Name DEBUG");
//        }
//        assertEquals(expected, actual);
//        assertNotNull(actual);
//
//    }
}
