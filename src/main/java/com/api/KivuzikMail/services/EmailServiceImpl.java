package com.api.KivuzikMail.services;

import com.api.KivuzikMail.models.EmailMessage;
import org.apache.commons.lang3.CharEncoding;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements IEmailService {


    @Autowired
    public JavaMailSender emailSender;
    Logger log = LogManager.getLogger(EmailServiceImpl.class);

    @Override
    public void sendSimpleMail(EmailMessage emailMessage, String email) {
//        log.info("received user "+emailMessage.getTo());
//
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("KIVUZIK");
//        message.setTo(emailMessage.getTo());
//        message.setSubject(emailMessage.getTitle());
//        message.setText(emailMessage.getBody());
//        //emailSender.send(message);
//
//        log.info("Sent email to User '{}'", emailMessage.getTo());
        MimeMessage mimeMessage = emailSender.createMimeMessage();

        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, false, CharEncoding.UTF_8);
            message.setTo(email);
            message.setFrom(new InternetAddress("kivuzik.cd@gmail.com", "KIVUZIK"));
            message.setSubject(emailMessage.getTitle());
            message.setText(emailMessage.getBody(), true);
            emailSender.send(mimeMessage);

            log.info("Sent email to User '{}'", email);
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.info("Email could not be sent to user '{}'", email, e);
            } else {
                log.info("Email could not be sent to user '{}': {}", email, e.getMessage());
            }
        }
    }
}
