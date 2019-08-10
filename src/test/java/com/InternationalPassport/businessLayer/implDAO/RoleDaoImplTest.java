package com.InternationalPassport.businessLayer.implDAO;

import com.InternationalPassport.businessLayer.DAO.CustomerDAO;
import com.InternationalPassport.businessLayer.DAO.RoleDAO;
import com.InternationalPassport.businessLayer.model.Customer;
import com.InternationalPassport.businessLayer.model.Role;
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
@Transactional
@ContextConfiguration(classes = SpringJPAConfigTest.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class RoleDaoImplTest {

    @Autowired
    RoleDAO roleDao;

    @Autowired
    CustomerDAO customerDAO;

    private static final Logger logger = LogManager.getLogger(RoleDaoImplTest.class);

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
        Role role = roleDao.findById(7);
        actual = role.getId();
        logger.debug(role + " findById test ID - " + role.getId());
        assertEquals(expect, actual);
    }

//    @Test
//    public void findByQuery() {
//    }

    @Test
    public void findAll() {
        int expectSize = 3;
        int actualSize = 0;
        List<Role> roles = roleDao.findAll();
        actualSize = roles.size();
        for (Role role: roles) {
            logger.debug(role + " - entity findAll test");
        }
        assertEquals(expectSize, actualSize);
    }

    @Test
    public void persist() {
        Role role = new Role("Security");
        roleDao.persist(role);
        List<Role> roleList = roleDao.findAll();
        assertEquals(4, roleList.size());
        logger.debug("PERSIST , SIZE ALL ROLES -- " + roleList);
    }

    @Test
    public void update() {
        String expectRole = "Director";
        String actualRole = null;
        Role role = roleDao.findById(7);
        role.setRole("Director");
        roleDao.update(role);
        Role resRole = roleDao.findById(7);
        logger.debug(resRole + " Update test Role");
        for(Customer customer: resRole.getCustomers()) {
            logger.debug("Check name role Customer --- " + customer.getRole());
        }
        actualRole = resRole.getRole();
        assertEquals(expectRole, actualRole);
    }

    @Test
    public void delete() {
        Role expectRole = null;
        Role actualRole = roleDao.findById(7);
        assertNotNull(actualRole);
        logger.debug("SIZE LIST CUSTOMER" + actualRole.getCustomers().size());
        Customer customer = actualRole.getCustomers().get(0);
        logger.debug("CUSTOMER: -- " + customer.toString());
        customerDAO.delete(customer);
        actualRole = roleDao.findById(7);
        assertNull(actualRole);
        logger.debug(actualRole + " from delete test");
    }
}