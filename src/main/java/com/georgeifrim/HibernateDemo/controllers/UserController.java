package com.georgeifrim.HibernateDemo.controllers;

import com.georgeifrim.HibernateDemo.entities.User;
import com.georgeifrim.HibernateDemo.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id){

        return ResponseEntity.status(HttpStatus.OK)
                                .body(userService.getUserById(id));
    }
    @PutMapping()
    public ResponseEntity<User> createUser(@RequestBody User user){

        return ResponseEntity.status(HttpStatusCode.valueOf(200))
                                .body(userService.createUser(user));
    }

    @PostMapping("/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User user){

        return userService.updateUser(id, user);
    }

    @PostMapping("/changePassword/{username}")
    public void changePass(@PathVariable String username,
                           @RequestParam String newPassword){
        userService.changePass(username, newPassword);
    }
}
