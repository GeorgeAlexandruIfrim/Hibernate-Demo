package com.georgeifrim.HibernateDemo.exceptions.training;

public class TrainingWithIdNotFound extends RuntimeException {

    public TrainingWithIdNotFound(int id) {
        super("Training with id " + id + " not found");
    }
}
