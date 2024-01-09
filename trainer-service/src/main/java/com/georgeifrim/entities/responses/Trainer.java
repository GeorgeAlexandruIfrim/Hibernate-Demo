package com.georgeifrim.entities.responses;

import com.georgeifrim.entities.TraineeResponseDto;
import com.georgeifrim.entities.requests.Training;

import java.util.List;

public class Trainer {

    String username;
    String firstName;
    String lastName;
    String trainingTypeName;
    List<Training> trainings;
    List<TraineeResponseDto> traineeList;
}
