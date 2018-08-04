package com.api.KivuzikMail;

import com.api.KivuzikMail.models.KivuzikUser;
import com.api.KivuzikMail.services.IUserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;


@EnableRabbit
@SpringBootApplication
public class KivuzikMailApplication {

	public static void main(String[] args) {
		SpringApplication.run(KivuzikMailApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(IUserService userService) {
		return args -> {
			// read json and write to db
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<KivuzikUser>> typeReference = new TypeReference<List<KivuzikUser>>(){};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/json/users.json");
			try {
				List<KivuzikUser> users = mapper.readValue(inputStream,typeReference);
				userService.save(users);
				System.out.println("Users Saved!");
			} catch (IOException e){
				System.out.println("Unable to save users: " + e.getMessage());
			}
		};
	}


}
