package com.InternationalPassport.businessLayer.service.email;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service("emailService")
public class EmailService implements EmailServiceInterface{

    private final Logger logger = LogManager.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    @Qualifier("mailTemplate")
    private SimpleMailMessage preConfiguredMessage;

    @Autowired
    @Qualifier("mailRegistrationTemplate")
    private SimpleMailMessage preConfiguredRegistrationMessage;



//    public void sendMail(String to, String subject, String body) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(to);
//        message.setSubject(subject);
//        message.setText(body);
//        try{
//            mailSender.send(message);
//        } catch (MailException me) {
//            logger.error("Error from mail send sendMail() " + me.getMessage());
//        }
//
//    }


//    public void sendPreConfiguredMail(String message) {
//        SimpleMailMessage mailMessage = new SimpleMailMessage(preConfiguredMessage);
//        mailMessage.setText(message);
//        try{
//            mailSender.send(mailMessage);
//        }catch (MailException me) {
//            logger.error("error from sendPreConfiguredMail" + me.getMessage());
//        }
//    }

//    public void sendWithAttachment(String from, String to, String text) {
//        MimeMessagePreparator preparator = new MimeMessagePreparator() {
//            @Override
//            public void prepare(MimeMessage mimeMessage) throws Exception {
//                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
//                mimeMessageHelper.setFrom(from);
//                mimeMessageHelper.setTo(to);
//                mimeMessageHelper.setSubject("super-pupper-test");
//                mimeMessageHelper.setText(text + "my text <h1>yo you best </h1>", true);
////                mimeMessageHelper.setText(text + "my text <img src = 'cid: will-test-image'>", true);
////                mimeMessageHelper.addInline("will-test-image",  new ClassPathResource("will-test-image.jpeg"));
//            }
//        };
//        try{
//            mailSender.send(preparator);
//        }catch (MailException me) {
//            logger.error(me.getMessage());
//        }
//    }


    /**
     * This method will send compose and send the message
     * */
    @Override
    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        try{
            mailSender.send(message);
        } catch (MailException me) {
            logger.error("Error from mail send sendMail() " + me.getMessage());
        }
    }


    /**
     * This method will send a pre-configured message
     * */
    @Override
    public void sendEmailPattern(String text) {
        SimpleMailMessage mailMessage = new SimpleMailMessage(preConfiguredMessage);
        mailMessage.setText(text);
        try{
            mailMessage.setSubject("preset subject");
            mailSender.send(mailMessage);
        }catch (MailException me) {
            logger.error("error from sendPreConfiguredMail" + me.getMessage());
        }
    }

    @Override
    public void sendRegistrationEmail(String to, String name, String link) {
        SimpleMailMessage mailMessage = new SimpleMailMessage(preConfiguredRegistrationMessage);

        mailMessage.setSubject(mailMessage.getSubject() + name);
        mailMessage.setText(mailMessage.getText() + link);
        mailMessage.setTo(to);
        if (mailMessage.getTo().length < 1) {
            return;
        }
        try {
            mailSender.send(mailMessage);
        }catch (MailException me) {
            logger.error("error from sendPreConfiguredMail" + me.getMessage());
        }
    }

    @Override
    public void sendEmailWithAttention(String to, String from, String text, String subject) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
                mimeMessageHelper.setFrom(from);
                mimeMessageHelper.setTo(to);
                mimeMessageHelper.setSubject(subject);
                mimeMessageHelper.setText(text + "my text <h1>yo you best </h1>", true);
//                mimeMessageHelper.setText(text + "my text <img src = 'cid: will-test-image'>", true);
//                mimeMessageHelper.addInline("will-test-image",  new ClassPathResource("will-test-image.jpeg"));
            }
        };
        try{
            mailSender.send(preparator);
        }catch (MailException me) {
            logger.error(me.getMessage());
        }
    }

    @Override
    public void sendEmailWithFullAttention(String to, String from, String text, String subject, ClassPathResource resource) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
                mimeMessageHelper.setFrom(from);
                mimeMessageHelper.setTo(to);
                mimeMessageHelper.setSubject("super-pupper-test");
                mimeMessageHelper.setText(text + "my text <h1>yo you best </h1>", true);
                mimeMessageHelper.setText(text + "my text <img src = 'cid: will-test-image'>", true);
                mimeMessageHelper.addInline("will-test-image",  resource);
//                mimeMessageHelper.addInline("will-test-image",  new ClassPathResource("will-test-image.jpeg"));
            }
        };
        try{
            mailSender.send(preparator);
        }catch (MailException me) {
            logger.error(me.getMessage());
        }
    }
}
