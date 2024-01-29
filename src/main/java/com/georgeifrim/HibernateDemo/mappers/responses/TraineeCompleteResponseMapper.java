package com.georgeifrim.HibernateDemo.mappers.responses;

import com.georgeifrim.HibernateDemo.entities.Trainee;
import com.georgeifrim.HibernateDemo.entities.Trainer;
import com.georgeifrim.HibernateDemo.entities.Training;
import com.georgeifrim.HibernateDemo.entities.User;
import com.georgeifrim.HibernateDemo.entities.dto.responses.TraineeCompleteResponseDto;
import com.georgeifrim.HibernateDemo.entities.dto.responses.TraineeResponseDto;
import com.georgeifrim.HibernateDemo.entities.dto.responses.TrainerCompleteResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
public class TraineeCompleteResponseMapper implements ResponseMapper<Trainee, TraineeCompleteResponseDto>{

    @Autowired
    private TraineeResponseMapper traineeResponseMapper;

    @Override
    public TraineeCompleteResponseDto toResponseDto(Trainee trainee) {
        User user = trainee.getUser();
        List<TrainerCompleteResponseDto> trainerResponseList = trainee.getTrainers().stream()
                .map(trainerToDto)
                .toList();

        return new TraineeCompleteResponseDto(
                user.getFirstName(),
                user.getLastName(),
                trainee.getDate_of_birth(),
                trainee.getAddress(),
                user.getActive(),
                trainerResponseList
                );

    }

    Function<Trainer, TrainerCompleteResponseDto> trainerToDto = trainer -> {
        User user = trainer.getUser();
        String username = user.getUsername();
        String first_name = user.getFirstName();
        String last_name = user.getLastName();
        String trainingTypeName = trainer.getTrainingType().getName();
        List<Training> trainings = trainer.getTrainings();
        List<TraineeResponseDto> traineeList = trainer.getTrainees()
                .stream()
                .map(trainee -> traineeResponseMapper.toResponseDto(trainee))
                .toList();

        return new TrainerCompleteResponseDto(username, first_name, last_name, trainingTypeName, trainings, traineeList);
    };


}
