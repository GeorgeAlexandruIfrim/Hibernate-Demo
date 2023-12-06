package com.georgeifrim.HibernateDemo.entities.dto.responses;

import java.util.List;

public record TrainerCompleteResponseDto(
        String username,
        String first_name,
        String last_name,
        String trainingTypeName,
        List<TraineeResponseDto> traineeList
) {
}
