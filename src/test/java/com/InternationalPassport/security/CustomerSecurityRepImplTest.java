package com.InternationalPassport.security;

import com.InternationalPassport.businessLayer.model.Customer;
import com.InternationalPassport.springConfigs.EmailConfigTest;
import com.InternationalPassport.springConfigs.SpringDataConfig;
import com.InternationalPassport.springConfigs.SpringJPAConfigTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

@ContextConfiguration(classes = {SpringJPAConfigTest.class})
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerSecurityRepImplTest {

    @Autowired
    CustomerSecurityRep customerSecurityRep;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void customerSecurityRepFindByLogin() {
        String expectLogin = "CusLOGIN";
        Customer customer;

        customer = customerSecurityRep.findByLogin(expectLogin);
        System.out.println("TEST -- customerSecurityRepFindByLogin " + customer.toStringLogin());
        assertEquals(customer.getLogin(), expectLogin);
    }

    @Test
    public void userDetailsServiceLoadUserByUserName() {
        String expectLogin = "CusLOGIN";
        UserDetailsImpl userDetails;

        userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(expectLogin);
        System.out.println("TEST -- userDetailsServiceLoadUserByUserName " + userDetails.getCust().toStringLogin());
        assertEquals(userDetails.getUsername(), expectLogin);
    }
}