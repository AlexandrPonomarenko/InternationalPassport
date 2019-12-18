package com.InternationalPassport.springConfigs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@ComponentScan("com.InternationalPassport")
@PropertySource("classpath:mailConfig-test.properties")
public class EmailConfigTest {

    @Autowired
    Environment env;

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(env.getProperty("mail.host"));
        mailSender.setPort(Integer.parseInt(env.getProperty("mail.port")));

        mailSender.setUsername(env.getProperty("mail.user"));
        mailSender.setPassword(env.getProperty("mail.password"));

        Properties properties = mailSender.getJavaMailProperties();
        properties.setProperty("mail.transport.protocol", env.getProperty("mail.transport.protocol"));
        properties.setProperty("mail.smtp.auth", env.getProperty("mail.smtp.auth"));
        properties.setProperty("mail.smtp.starttls.enable", env.getProperty("mail.smtp.starttls.enable"));
        properties.setProperty("mail.smtp.starttls.required", env.getProperty("mail.smtp.starttls.required"));
        properties.setProperty("mail.debug", env.getProperty("mail.debug"));
        properties.setProperty("mail.smtp.ssl.enable", env.getProperty("mail.smtp.ssl.enable"));

        return  mailSender;
    }

    @Bean
    public SimpleMailMessage mailTemplate() {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo("furriets@gmail.com");
        mailMessage.setFrom("mainwarmstar@gmail.com");
        mailMessage.setText("Its message from template");

        return mailMessage;
    }

    @Bean
    public SimpleMailMessage mailRegistrationTemplate() {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom("mainwarmstar@gmail.com");
        mailMessage.setSubject("Thank you for registration dear ");
        mailMessage.setText("You will need go to this link for the confirm email");

        return mailMessage;
    }
}
