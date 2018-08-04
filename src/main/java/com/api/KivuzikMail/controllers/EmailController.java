package com.api.KivuzikMail.controllers;

import com.api.KivuzikMail.models.EmailMessage;
import com.api.KivuzikMail.models.KivuzikUser;
import com.api.KivuzikMail.services.IEmailService;
import com.api.KivuzikMail.services.IUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/emails")
@CrossOrigin(origins = "*")
public class EmailController {

     @Autowired
     IUserService userService;
     @Autowired
     IEmailService emailService;
     Logger LOG = LogManager.getLogger(EmailController.class);

     HttpHeaders httpHeaders = new HttpHeaders();
     @PostMapping("/send")
     public ResponseEntity<?>sendEmail(@RequestBody EmailMessage emailMessage){

         LOG.info("REQUEST ARRIVED subject: "+emailMessage.getTitle()+" body: "+emailMessage.getBody());
         Collection<KivuzikUser>kivuzikUsers = userService.getAll();

         for(KivuzikUser kivuzikUser: kivuzikUsers){
             emailMessage.setTo(kivuzikUser.getEmail());

                try {

                    emailService.sendSimpleMail(emailMessage);
                    LOG.info("email envoyE A "+ kivuzikUser.getUsername() +" / "+kivuzikUser.getEmail());
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    LOG.error("error "+e.toString());
                }


         }
         httpHeaders.add("response_message","Operation reussie");
         return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
     }
}
