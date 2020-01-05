package com.InternationalPassport.businessLayer.service.email;

import com.InternationalPassport.springConfigs.BeensConfig;
import com.InternationalPassport.springConfigs.EmailConfigTest;
import com.InternationalPassport.springConfigs.SpringJPAConfigTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

@ContextConfiguration(classes = {EmailConfigTest.class})
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class EmailServiceTest {

//    @Autowired
//    public EmailService emailService;

    @Autowired
    public EmailServiceInterface emailServiceInterface;



    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void sendMail() {
        try{
            emailServiceInterface.sendEmail("furriets@gmail.com", "about your success", "How are you bro?");
        }catch (MailException me) {
            fail();
        }
    }

    @Test
    public void sendPreConfiguredMail() {
        try{
            emailServiceInterface.sendEmailPattern("Hello amigo");
        }catch (MailException me) {
            fail();
        }

    }

    @Test
    public void sendPreConfiguredRegMail() {
        try{
            emailServiceInterface.sendRegistrationEmail("furriets@gmail.com", "Amigo-bambino", "google.com");
        }catch (MailException me) {
            fail();
        }

    }


    @Test
    public void setWithAttention() {
        try{
            emailServiceInterface.sendEmailWithAttention("furriets@gmail.com", "mainwarmstar@gmail.com",
                    "TEST TES TEST : -> ", "super-pupper-test");
        }catch(MailException me) {
            fail();
        }
    }

    @Test
    public void setWithFullAttention() {
        try{
            emailServiceInterface.sendEmailWithFullAttention("furriets@gmail.com", "mainwarmstar@gmail.com",
                    "TEST TES TEST FULLLLLLL : -> ", "super-pupper-test-FULL", new ClassPathResource("will-test-image.jpeg"));
        }catch(MailException me) {
            fail();
        }
    }
}