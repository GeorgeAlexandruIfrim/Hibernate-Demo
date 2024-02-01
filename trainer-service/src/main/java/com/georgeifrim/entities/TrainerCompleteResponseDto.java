package com.georgeifrim.entities;

import com.georgeifrim.entities.responses.TrainingResponse;
import lombok.Data;

import java.util.List;

@Data
public class TrainerCompleteResponseDto {

    String username;
    String firstName;
    String lastName;
    String trainingTypeName;
    List<TrainingResponse> trainings;
    List<TraineeResponseDto> traineeList;

}
