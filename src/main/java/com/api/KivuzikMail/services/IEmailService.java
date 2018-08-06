package com.api.KivuzikMail.services;

import com.api.KivuzikMail.models.EmailMessage;

import java.io.UnsupportedEncodingException;

public interface IEmailService {

    public void sendSimpleMail(EmailMessage emailMessage, String email) throws UnsupportedEncodingException;

}
