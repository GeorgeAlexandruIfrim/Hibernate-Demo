package com.georgeifrim.HibernateDemo.entities.dto.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @JsonIgnoreProperties("traineeList")
    List<TrainerCompleteResponseDto> trainerList;
}
