package com.georgeifrim.HibernateDemo.entities.dto.responses;

import com.georgeifrim.HibernateDemo.entities.Training;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class TrainerCompleteResponseDto{

    private String username;
    private String firstName;
    private String lastName;
    private String trainingTypeName;
    private List<Training> trainings;
    private List<TraineeResponseDto> traineeList;
}
