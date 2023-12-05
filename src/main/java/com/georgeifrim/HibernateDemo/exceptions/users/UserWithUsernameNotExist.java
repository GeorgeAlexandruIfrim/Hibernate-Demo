package com.georgeifrim.HibernateDemo.exceptions.users;

import com.georgeifrim.HibernateDemo.entities.User;

public class UserWithUsernameNotExist extends RuntimeException {

    public UserWithUsernameNotExist(String username){
        super("User " + username + " does not exist !");
    }
}
