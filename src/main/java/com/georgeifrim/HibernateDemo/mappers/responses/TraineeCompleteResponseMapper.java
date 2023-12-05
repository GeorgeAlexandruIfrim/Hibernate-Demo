package com.georgeifrim.HibernateDemo.mappers.responses;

import com.georgeifrim.HibernateDemo.entities.Trainee;
import com.georgeifrim.HibernateDemo.entities.Trainer;
import com.georgeifrim.HibernateDemo.entities.User;
import com.georgeifrim.HibernateDemo.entities.dto.responses.TraineeCompleteResponseDto;
import com.georgeifrim.HibernateDemo.entities.dto.responses.TrainerCompleteResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
public class TraineeCompleteResponseMapper implements ResponseMapper<Trainee, TraineeCompleteResponseDto>{


    @Override
    public Trainee toEntity(TraineeCompleteResponseDto traineeCompleteResponseDto) {
        return null;
    }

    @Override
    public TraineeCompleteResponseDto toResponseDto(Trainee trainee) {
        User user = trainee.getUser();
        List<TrainerCompleteResponseDto> trainerResponseList = trainee.getTrainers().stream()
                .map(trainerToDto)
                .toList();

        return new TraineeCompleteResponseDto(
                user.getFirst_name(),
                user.getLast_name(),
                trainee.getDate_of_birth(),
                trainee.getAddress(),
                user.isActive(),
                trainerResponseList
                );

    }

    Function<Trainer, TrainerCompleteResponseDto> trainerToDto = trainer -> {
        User user = trainer.getUser();
        String username = user.getUsername();
        String first_name = user.getFirst_name();
        String last_name = user.getLast_name();
        String trainingTypeName = trainer.getTrainingType().getName();

        return new TrainerCompleteResponseDto(username, first_name, last_name, trainingTypeName);
    };
}
