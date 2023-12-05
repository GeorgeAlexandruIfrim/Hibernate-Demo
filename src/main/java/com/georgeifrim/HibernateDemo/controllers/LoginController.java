package com.georgeifrim.HibernateDemo.controllers;

import com.georgeifrim.HibernateDemo.repositories.UserRepo;
import com.georgeifrim.HibernateDemo.services.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;

    @GetMapping
    public ResponseEntity login(@RequestParam String username, @RequestParam String password){

        loginService.login(username, password);

            return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/changePassword")
    public ResponseEntity changePassword(@RequestParam String username,
                                         @RequestParam String oldPassword,
                                         @RequestParam String newPassword){

        loginService.login(username, oldPassword);
        loginService.changePassword(username, newPassword);
            return ResponseEntity.status(HttpStatus.OK).build();
    }
}
