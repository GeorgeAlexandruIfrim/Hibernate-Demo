package com.georgeifrim.HibernateDemo.exceptions.trainees;

public class TraineeWithIdNotFound extends RuntimeException{

    public TraineeWithIdNotFound(int id) {
        super("Trainee with id " + id + " was not found");
    }
}
