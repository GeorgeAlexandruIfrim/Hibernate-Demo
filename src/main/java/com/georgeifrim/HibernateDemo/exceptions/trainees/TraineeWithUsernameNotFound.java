package com.georgeifrim.HibernateDemo.exceptions.trainees;

public class TraineeWithUsernameNotFound extends RuntimeException{

    public TraineeWithUsernameNotFound(String username) {
        super("Trainee with username " + username + " was not found");
    }
}
