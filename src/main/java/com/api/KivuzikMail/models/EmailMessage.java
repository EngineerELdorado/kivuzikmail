package com.api.KivuzikMail.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
public class EmailMessage {

    private String to;
    private String title;
    private String body;
}
