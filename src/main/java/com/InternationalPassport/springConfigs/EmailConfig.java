package com.InternationalPassport.springConfigs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailConfig {

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(465);

        mailSender.setUsername("mainwarmstar@gmail.com");
        mailSender.setPassword("warmStar1992");

        Properties properties = mailSender.getJavaMailProperties();
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.debug", "true");
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
