package com.georgeifrim.HibernateDemo.exceptions.users;


public class UserWithUsernameAlreadyExists extends RuntimeException {

    public UserWithUsernameAlreadyExists(String username){
        super("The User " + username + " already exists !");
    }
}
