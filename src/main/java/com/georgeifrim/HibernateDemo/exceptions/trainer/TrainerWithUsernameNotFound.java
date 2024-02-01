package com.georgeifrim.HibernateDemo.exceptions.trainer;

public class TrainerWithUsernameNotFound extends RuntimeException{

    public TrainerWithUsernameNotFound(String username){
        super("Trainer " + username + " was not found!");
    }
}
