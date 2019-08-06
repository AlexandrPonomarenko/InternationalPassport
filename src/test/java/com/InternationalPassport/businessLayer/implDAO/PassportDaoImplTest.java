package com.InternationalPassport.businessLayer.implDAO;

import com.InternationalPassport.businessLayer.DAO.PassportDAO;
import com.InternationalPassport.businessLayer.model.Passport;
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

import java.util.List;

import static org.junit.Assert.*;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
@ContextConfiguration(classes = SpringJPAConfigTest.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class PassportDaoImplTest {

    private static final Logger logger = LogManager.getLogger(PassportDaoImplTest.class);

    @Autowired
    PassportDAO passportDAO;


    @Test
    public void findById() {
        int expect = 7;
        int actual = 0;
        Passport passportActual = passportDAO.findById(expect);
        actual = passportActual.getId();
        logger.debug("Test findById expect - " + expect + " actual" + actual);
        assertSame(expect, actual);
    }

//    @Test
//    public void findByQuery() {
//    }

    @Test
    public void findAll() {
        int expectSize = 3;
        int actualSize = 0;
        List<Passport> passportList = passportDAO.findAll();
        actualSize = passportList.size();
        logger.debug("Test findAll " + passportList.size());
        assertSame(expectSize, actualSize);
    }

//    @Test
//    public void persist() {
//
//    }

    @Test
    public void update() {
        Passport expectPas = passportDAO.findById(7);
        logger.debug("Test update expectPas ----- " + expectPas);
        expectPas.setSeria("KK9900G");
        passportDAO.persist(expectPas);
        Passport actualPas = passportDAO.findById(7);
        assertEquals(expectPas.getSeria(), actualPas.getSeria());
        logger.debug("Test update actualPas --- " + actualPas);
    }

//    @Test
//    public void delete() {
//    }
}