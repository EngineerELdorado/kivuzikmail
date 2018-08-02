package com.api.KivuzikMail.controllers;

import com.api.KivuzikMail.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    IUserService userService;

    @GetMapping("")
    public ResponseEntity<?>getAll(){

        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }
}
