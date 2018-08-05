package com.api.KivuzikMail.services;

import com.api.KivuzikMail.models.EmailMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements IEmailService {


    @Autowired
    public JavaMailSender emailSender;

    @Override
    public void sendSimpleMail(EmailMessage emailMessage) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("KIVUZIK");
        message.setTo(emailMessage.getTo());
        message.setSubject(emailMessage.getTitle());
        message.setText(emailMessage.getBody());
        emailSender.send(message);
    }
}
