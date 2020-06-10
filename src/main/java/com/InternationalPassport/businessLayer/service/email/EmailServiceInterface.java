package com.InternationalPassport.businessLayer.service.email;

import org.springframework.core.io.ClassPathResource;

public interface EmailServiceInterface {
    void sendEmail(String to, String subject, String text);
    void sendEmailPattern(String text);
    void sendRegistrationEmail(String to, String name, String link);
    void sendEmailWithAttention(String to, String from, String text, String subject);
    void sendEmailWithFullAttention(String to, String from, String text, String subject, ClassPathResource resource);
}
