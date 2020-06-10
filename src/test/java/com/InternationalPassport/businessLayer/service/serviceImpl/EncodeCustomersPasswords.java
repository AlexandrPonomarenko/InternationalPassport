package com.InternationalPassport.businessLayer.service.serviceImpl;

import com.InternationalPassport.businessLayer.implDAO.PassportDaoImplTest;
import com.InternationalPassport.businessLayer.model.Address;
import com.InternationalPassport.businessLayer.model.Customer;
import com.InternationalPassport.businessLayer.model.Passport;
import com.InternationalPassport.businessLayer.model.Role;
import com.InternationalPassport.businessLayer.service.CustomerService;
import com.InternationalPassport.springConfigs.SpringDataConfig;
import com.InternationalPassport.springConfigs.SpringDataConfigTest;
import com.InternationalPassport.springConfigs.SpringJPAConfigTest;
import com.InternationalPassport.springConfigs.WebConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@WebAppConfiguration
@Transactional
@ContextConfiguration(classes = SpringJPAConfigTest.class)
@ComponentScan("com.InternationalPassport.businessLayer")
@RunWith(SpringJUnit4ClassRunner.class)
public class EncodeCustomersPasswords {
    private static final Logger logger = LogManager.getLogger(EncodeCustomersPasswords.class);

    @Autowired
    CustomerService customerService;

//    @Autowired
//    BCryptPasswordEncoder bCryptPasswordEncoder;

    public BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    public List<Customer> customers;


    public void getAllCustTest() {
        customers = customerService.findAll();
        encodeAllCust(customers);
        showAll(customers);
    }

//    @Rollback(false)
    @Test
    public void updateAllPasswords() {
        getAllCustTest();
        for(Customer customer: customers) {
            customerService.update(customer);
        }
    }

    @Test
    public void encodeStringTest() { //$2a$10$4js6BvoRqCHyJcxHOJET1O4E5w.nOz5TYvL0wS4JOQ7GrWD266EyG
        String str = "testPassword"; //$2a$10$vMTwxDMpcfAiqMBuIhZuUeCpgkgiW.8kl2rzATgZY3hVBvrhnwcAO
        String encodeStr = bCryptPasswordEncoder.encode(str);
        logger.debug(str + " --> ENCODE STRING ---> " + encodeStr);
    }

    @Test
    public void matchesPassTest() {
        String password = "12345qwert";
        Customer customer = customerService.findByLogin("CusLOGIN");
        boolean actual = bCryptPasswordEncoder.matches(password, customer.getPassword());
        logger.debug("ACTUAL" + actual);
        logger.debug("MATCHES PASS " + bCryptPasswordEncoder.matches(password, customer.getPassword()));
        assertTrue(actual);
    }

    @Test
    public void samePassTest() {
        String one = "alex";
        String two = "alex";
        String oneEnc = "";
        String twoEnc = "";
        oneEnc = bCryptPasswordEncoder.encode(one);
        twoEnc = bCryptPasswordEncoder.encode(two);
        logger.debug(one +  " ONE ENCODE STRING " + oneEnc);
        logger.debug(twoEnc +  " TWO ENCODE STRING " + twoEnc);
        assertEquals(oneEnc, twoEnc);
    }

    @Test
    public void matchesStringTest() {
        boolean result = false;
        String one = "alex";
        String oneEnc = "";
        oneEnc = bCryptPasswordEncoder.encode(one);
        result = bCryptPasswordEncoder.matches(one, oneEnc);
        logger.debug(one +  " ONE ENCODE STRING " + oneEnc);
        logger.debug("result : " + result);
        assertTrue(result);
    }

    @Test
    public void matchesTwoPassTest() {
        boolean resultOne = false;
        boolean resultTwo = false;
        boolean resultThree = false;
        String one = "alex";
        String two = "alex";
        String three = "alex";
        String oneEnc = "";
        String twoEnc = "";
        oneEnc = bCryptPasswordEncoder.encode(one);
        twoEnc = bCryptPasswordEncoder.encode(two);
        resultOne = bCryptPasswordEncoder.matches(two, oneEnc);
        resultTwo = bCryptPasswordEncoder.matches(one, twoEnc);
        resultThree = bCryptPasswordEncoder.matches(three, twoEnc);
        logger.debug(one +  " ONE ENCODE STRING " + oneEnc);
        logger.debug(two +  " TWO ENCODE STRING " + twoEnc);
        logger.debug("result : " + resultOne);
        logger.debug("result : " + resultTwo);
        assertTrue(resultOne);
        assertTrue(resultTwo);
        assertTrue(resultThree);
    }

    public void encodeAllCust(List<Customer> customers) {
        String pas;
        for (Customer customer : customers) {
            pas = bCryptPasswordEncoder.encode(customer.getPassword());
            customer.setPassword(pas);
            customer.setRepeatPassword(pas);
//            logger.debug("Show all: customer is --->> " + customer.toStringLogin());
        }
    }

    public void showAll(List<Customer> customers) {
        for (Customer customer : customers) {
            logger.debug("Show all: customer is --->> " + customer.toStringLogin());
        }
    }
}
