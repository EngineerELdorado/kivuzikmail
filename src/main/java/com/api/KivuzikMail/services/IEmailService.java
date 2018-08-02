package com.api.KivuzikMail.services;

import com.api.KivuzikMail.models.EmailMessage;

public interface IEmailService {

    public void sendSimpleMail(EmailMessage emailMessage);

}
