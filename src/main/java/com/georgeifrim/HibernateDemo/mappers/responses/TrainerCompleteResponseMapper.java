package com.georgeifrim.HibernateDemo.mappers.responses;

import com.georgeifrim.HibernateDemo.entities.Trainer;
import com.georgeifrim.HibernateDemo.entities.User;
import com.georgeifrim.HibernateDemo.entities.dto.responses.TraineeResponseDto;
import com.georgeifrim.HibernateDemo.entities.dto.responses.TrainerCompleteResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerCompleteResponseMapper implements ResponseMapper<Trainer, TrainerCompleteResponseDto> {

    @Autowired
    private TraineeResponseMapper traineeResponseMapper;

    @Override
    public TrainerCompleteResponseDto toResponseDto(Trainer trainer) {
        User user = trainer.getUser();
        String username = user.getUsername();
        String first_name = user.getFirstName();
        String last_name = user.getLastName();
        String trainingTypeName = trainer.getTrainingType().getName();
        List<TraineeResponseDto> traineeList = trainer.getTrainees()
                .stream()
                .map(trainee -> traineeResponseMapper.toResponseDto(trainee))
                .toList();

        return new TrainerCompleteResponseDto(
                username,
                first_name,
                last_name,
                trainingTypeName,
                traineeList
        );
    }
}
