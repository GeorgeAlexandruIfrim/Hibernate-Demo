package com.georgeifrim.HibernateDemo.mappers.requests;

import com.georgeifrim.HibernateDemo.entities.Trainer;
import com.georgeifrim.HibernateDemo.entities.User;
import com.georgeifrim.HibernateDemo.entities.dto.requests.TrainerRequestDto;
import com.georgeifrim.HibernateDemo.repositories.TraineeRepo;
import com.georgeifrim.HibernateDemo.repositories.TrainingTypeRepo;
import com.georgeifrim.HibernateDemo.repositories.UserRepo;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
@AllArgsConstructor
public class TrainerRequestsMapper implements RequestsMapper<Trainer, TrainerRequestDto> {


    private final TrainingTypeRepo trainingTypeRepo;
    private final TraineeRepo traineeRepo;
    private final UserRepo userRepo;

    @Override
    public Trainer toEntity(TrainerRequestDto trainerRequestDto){
        var userToBeSaved = new User(trainerRequestDto.first_name(), trainerRequestDto.last_name(), false);
        return Trainer.builder()
                .trainingType(trainingTypeRepo.findByName(trainerRequestDto.trainingTypeName()))
                .user(userRepo.save(userToBeSaved))
                .build();
    }

    @Override
    public TrainerRequestDto toRequestDto(Trainer trainer){
        return null;
    }

    private boolean userIsATrainee(int userId) {
        return traineeRepo.findById(userId).isPresent();
    }

}
