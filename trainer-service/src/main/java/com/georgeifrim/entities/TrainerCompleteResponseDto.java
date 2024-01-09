package com.georgeifrim.entities;

import com.georgeifrim.entities.requests.Training;
import com.georgeifrim.entities.responses.Trainer;
import com.georgeifrim.entities.responses.TrainingResponse;
import lombok.Data;

import java.time.LocalDate;
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
