package com.georgeifrim.HibernateDemo.controllers;

import com.georgeifrim.HibernateDemo.entities.dto.requests.ChangePasswordRequest;
import com.georgeifrim.HibernateDemo.entities.dto.requests.LoginRequest;
import com.georgeifrim.HibernateDemo.services.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;

    @PutMapping
    public ResponseEntity login(@RequestBody LoginRequest loginRequest){

        loginService.login(loginRequest);

            return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/changePassword")
    public ResponseEntity changePassword(@RequestBody ChangePasswordRequest changePasswordRequest){
        var loginRequest = new LoginRequest(changePasswordRequest.getUsername(), changePasswordRequest.getOldPassword());
        loginService.login(loginRequest);
        loginService.changePassword(changePasswordRequest);
            return ResponseEntity.status(HttpStatus.OK).build();
    }
}
