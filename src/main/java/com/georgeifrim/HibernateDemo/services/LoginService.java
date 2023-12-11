package com.georgeifrim.HibernateDemo.services;

import com.georgeifrim.HibernateDemo.entities.User;
import com.georgeifrim.HibernateDemo.entities.dto.requests.ChangePasswordRequest;
import com.georgeifrim.HibernateDemo.entities.dto.requests.LoginRequest;
import com.georgeifrim.HibernateDemo.exceptions.login.PasswordNotMatching;
import com.georgeifrim.HibernateDemo.exceptions.users.UserWithUsernameNotExist;
import com.georgeifrim.HibernateDemo.repositories.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class LoginService {
    private final UserRepo userRepo;

    public boolean login(LoginRequest loginRequest){
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        return userWithUsernameExist(username) && userPasswordMatch(username, password);

    }
    public boolean userWithUsernameExist(String username){
        if(!userRepo.existsByUsername(username)){
            throw new UserWithUsernameNotExist(username);
        }
        return true;
    }
    public boolean userPasswordMatch(String username, String password){

        String pass = userRepo.findByUsername(username).getPassword();

        if(!Objects.equals(password, pass)){
            throw new PasswordNotMatching(username);
        }
        return true;
    }
    public void changePassword(ChangePasswordRequest changePasswordRequest) {
        String username = changePasswordRequest.getUsername();
        String newPassword = changePasswordRequest.getNewPassword();
        User user = userRepo.findByUsername(username);
        user.setPassword(newPassword);
        userRepo.save(user);
    }
}
