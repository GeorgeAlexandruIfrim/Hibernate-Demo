package com.georgeifrim.HibernateDemo.entities.dto.responses;

import java.time.LocalDate;
import java.util.List;

public record TraineeCompleteResponseDto(
        String first_name,
        String last_name,
        LocalDate date_of_birth,
        String address,
        boolean isActive,
        List<TrainerCompleteResponseDto> trainerList
) {
}
