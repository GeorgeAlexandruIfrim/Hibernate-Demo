package com.georgeifrim.HibernateDemo.services;

import com.georgeifrim.HibernateDemo.entities.User;
import com.georgeifrim.HibernateDemo.exceptions.login.PasswordNotMatching;
import com.georgeifrim.HibernateDemo.exceptions.users.UserWithUsernameNotExist;
import com.georgeifrim.HibernateDemo.repositories.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginService {

    private final UserRepo userRepo;


    public boolean login(String username, String password){
       if(userWithUsernameExist(username) && userPasswordMatch(username, password))
           return true;
       else
           return false;

    }
    public boolean userWithUsernameExist(String username){
        if(userRepo.existsByUsername(username))
            return true;
        else
            throw new UserWithUsernameNotExist(username);
    }

    public boolean userPasswordMatch(String username, String password){

        String pass = userRepo.findByUsername(username).getPassword();

        if(password.equals(pass))
            return true;
        else
            throw new PasswordNotMatching(username);
    }


    public void changePassword(String username, String newPassword) {
        User user = userRepo.findByUsername(username);
        user.setPassword(newPassword);
        userRepo.save(user);
    }
}
