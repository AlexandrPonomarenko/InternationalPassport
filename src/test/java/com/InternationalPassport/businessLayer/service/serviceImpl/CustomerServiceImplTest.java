package com.InternationalPassport.businessLayer.service.serviceImpl;

import com.InternationalPassport.businessLayer.implDAO.PassportDaoImplTest;
import com.InternationalPassport.businessLayer.model.Address;
import com.InternationalPassport.businessLayer.model.Customer;
import com.InternationalPassport.businessLayer.model.Passport;
import com.InternationalPassport.businessLayer.model.Role;
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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
@ContextConfiguration(classes = SpringJPAConfigTest.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerServiceImplTest {


    private static final Logger logger = LogManager.getLogger(CustomerServiceImplTest.class);

    @Autowired
    CustomerService customerService;

    @Test
    public void findByFirstName() {
        String expectName = "Joy";
        String actualName = "";
        Customer customer = customerService.findByFirstName(expectName).get(0);
        actualName = customer.getName();
        logger.debug("Test findByFirstName --- " + customer);
        assertEquals(expectName, actualName);
    }

    @Test
    public void findByLogin() {
        String expectLogin = "LOGIN1";
        String actualLogin = "";
        Customer customer = customerService.findByLogin(expectLogin);
        actualLogin = customer.getLogin();
        logger.debug("Test findByLogin --- " + customer);
        assertEquals(expectLogin, actualLogin);
    }

    @Test
    public void findByEmail() {
        String expectEmail = "furriets@gmail.com";
        String actualEmail = "";
        Customer customer = customerService.findByEmail(expectEmail);
        actualEmail = customer.getEmail();
        logger.debug("Test findByEmail --- " + customer);
        assertEquals(expectEmail, actualEmail);
    }

    @Test
    public void findById() {
        int expectId = 7;
        int actualId = 0;
        Customer customer = customerService.findById(expectId);
        actualId = customer.getId();
        logger.debug("Test findById --- " + customer);
        assertEquals(expectId, actualId);
    }

//    @Test
//    public void findByQuery() {
//    }

    @Test
    public void findAll() {
        int expectEmail = 3;
        int actualEmail = 0;
        List<Customer> customers = customerService.findAll();
        actualEmail = customers.size();
        logger.debug("Test findAll --- " + customers);
        assertEquals(expectEmail, actualEmail);
    }

    @Test
    public void persist() {
        Customer newCustomer = new Customer("Garry", "Potter", "Gar", 30,
                LocalDate.of(1990, 7,21), "garry@gmail.com", "loginGarry", "passGarry");

        Role newRole = new Role("Manager");
        Passport newPas = new Passport("NN55MMEE", "native");
        Address newAddress = new Address("Italy", "Rome", "Str. Romel", 43);
        newCustomer.setRole(newRole);
        newRole.getCustomers().add(newCustomer);
        newCustomer.setAddress(newAddress);
        newAddress.getCustomerList().add(newCustomer);
        newCustomer.setPassport(newPas);
        newPas.setCustomer(newCustomer);
        customerService.persist(newCustomer);
        List<Customer> customers = customerService.findAll();
        logger.debug("Test persist --- " + customers);
        assertEquals(4, customers.size());

    }

    @Test
    public void update() {
        String expectLogin = "newLogin";
        Customer customer = customerService.findById(7);
        customer.setLogin(expectLogin);
        customerService.update(customer);
        Customer actualCustomer = customerService.findByLogin(expectLogin);
        logger.debug("Test update ---- " + actualCustomer);
        assertEquals(expectLogin, actualCustomer.getLogin());
    }

    @Test
    public void delete() {
        Customer customer = customerService.findById(7);
        List<Customer> customers = customerService.findAll();
        logger.debug("Test delete before --- " + customers.size());
        assertEquals(3, customers.size());
        customerService.delete(customer);
        customers = customerService.findAll();
        assertEquals(2, customers.size());
        logger.debug("Test delete --- " + customers.size());
    }
}