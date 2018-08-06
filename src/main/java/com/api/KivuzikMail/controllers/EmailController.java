package com.api.KivuzikMail.controllers;

//import com.api.KivuzikMail.configs.RabbitMqConfiguration;
import com.api.KivuzikMail.models.EmailMessage;
import com.api.KivuzikMail.models.KivuzikUser;
import com.api.KivuzikMail.services.IEmailService;
import com.api.KivuzikMail.services.IUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//import static com.api.KivuzikMail.configs.RabbitMqConfiguration.KIVUZIK_EMAIL;

@RestController
@RequestMapping("/emails")
@CrossOrigin(origins = "*")
public class EmailController {

     @Autowired
     IUserService userService;
     @Autowired
     IEmailService emailService;
     //@Autowired
     //RabbitTemplate rabbitTemplate;
     Logger LOG = LogManager.getLogger(EmailController.class);

     HttpHeaders httpHeaders = new HttpHeaders();


    long start_time = TimeUnit.MILLISECONDS.toMinutes(System.currentTimeMillis());
//    @PostMapping("/send")
//    public void sendEmail(@RequestBody EmailMessage emailMessage){
//
//        //LOG.info("REQUEST ARRIVED subject: "+emailMessage.getTitle()+" body: "+emailMessage.getBody());
//        Collection<KivuzikUser>kivuzikUsers = userService.getAll();
//
//        for(KivuzikUser kivuzikUser: kivuzikUsers){
//
//                emailMessage.setTo(kivuzikUser.getEmail());
//
//                try {
//                    rabbitTemplate.convertAndSend(KIVUZIK_EMAIL,KIVUZIK_EMAIL, emailMessage);
//                    LOG.info("Email sent to RabbitMq for "+kivuzikUser.getUsername());
//                }
//                catch (Exception e)
//                {
//                    e.printStackTrace();
//                    LOG.error("error "+e.toString());
//                }
//
//
//
//        }
//    }
//
//     @RabbitListener(queues = KIVUZIK_EMAIL)
//     public void pushEmail(EmailMessage emailMessage){
//
//         LOG.info("REQUEST ARRIVED subject: "+emailMessage.getTitle()+" body: "+emailMessage.getBody());
//         KivuzikUser kivuzikUser = userService.findByEmail(emailMessage.getTo());
//
//                try {
//
//                    emailService.sendSimpleMail(emailMessage);
//                    LOG.info("email envoyE A "+ kivuzikUser.getUsername() +" / "+kivuzikUser.getEmail());
//                }
//                catch (Exception e)
//                {
//                    e.printStackTrace();
//                    LOG.error("error "+e.toString());
//                }
//
//     }

     @PostMapping("/sendMail")
     public void emailsThread(@RequestBody EmailMessage emailMessage) throws UnsupportedEncodingException {
         LOG.info(emailMessage.getBody() +" "+emailMessage.getTitle());
         int coreCount = Runtime.getRuntime().availableProcessors();
         LOG.info("CORE COUNT "+ coreCount);
         ExecutorService fixedPool = Executors.newFixedThreadPool(coreCount);
         Collection<KivuzikUser>kivuzikUsers = userService.getAll();
         for (KivuzikUser kivuzikUser: kivuzikUsers){
             
             fixedPool.execute(() -> {
                 //LOG.info(" user in the thread "+kivuzikUser.getEmail());

                try {
                     //emailMessage.setTo(kivuzikUser.getEmail());
                     emailService.sendSimpleMail(emailMessage, kivuzikUser.getEmail());
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                //LOG.info("email envoyE A "+ kivuzikUser.getUsername() +" / "+kivuzikUser.getEmail());
             });
         };
         //fixedPool.shutdown();
         long end_time = TimeUnit.MILLISECONDS.toMinutes(System.currentTimeMillis());
         long result = end_time-start_time;
         if(result<1){
             LOG.info("TIME TAKEN: "+ String.valueOf(result)+" seoonds");
         }
         else {
             LOG.info("TIME TAKEN: "+ String.valueOf(result)+" minutes");
         }


     }
}
