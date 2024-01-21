package com.georgeifrim.HibernateDemo.entities.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class TraineeCompleteResponseDto{

    String firstName;
    String lastName;
    LocalDate dateOfBirth;
    String address;
    boolean isActive;
    List<TrainerCompleteResponseDto> trainerList;
}
