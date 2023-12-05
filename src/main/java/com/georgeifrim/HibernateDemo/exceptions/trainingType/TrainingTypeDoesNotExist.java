package com.georgeifrim.HibernateDemo.exceptions.trainingType;


public class TrainingTypeDoesNotExist extends RuntimeException {

    public TrainingTypeDoesNotExist(String name){
        super("TrainingType with name " + " does not exist !");
    }
}
