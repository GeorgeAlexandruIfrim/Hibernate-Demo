package com.georgeifrim.HibernateDemo.exceptions.login;


public class PasswordNotMatching extends RuntimeException {

    public PasswordNotMatching(String username){
        super("Password for user " + " is incorrect !");
    }
}
