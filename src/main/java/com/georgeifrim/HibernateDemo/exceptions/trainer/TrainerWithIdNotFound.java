package com.georgeifrim.HibernateDemo.exceptions.trainer;

public class TrainerWithIdNotFound extends RuntimeException{

    public TrainerWithIdNotFound(int id) {
        super("Trainer with id " + id + " was not found");
    }
}
