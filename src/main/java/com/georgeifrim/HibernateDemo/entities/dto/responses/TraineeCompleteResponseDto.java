package com.georgeifrim.HibernateDemo.entities.dto.responses;

import java.time.LocalDate;
import java.util.List;

public record TraineeCompleteResponseDto(
        String firstName,
        String lastName,
        LocalDate dateOfBirth,
        String address,
        boolean isActive,
        List<TrainerCompleteResponseDto> trainerList
) {
}
