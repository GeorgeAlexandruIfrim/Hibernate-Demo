package com.georgeifrim.HibernateDemo.entities.dto.responses;

import com.georgeifrim.HibernateDemo.entities.Training;

import java.util.List;

public record TrainerCompleteResponseDto(
        String username,
        String firstName,
        String lastName,
        String trainingTypeName,
        List<Training> trainings,
        List<TraineeResponseDto> traineeList
) {
}
