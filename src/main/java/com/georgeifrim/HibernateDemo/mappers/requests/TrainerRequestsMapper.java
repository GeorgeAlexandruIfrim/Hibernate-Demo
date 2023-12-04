package com.georgeifrim.HibernateDemo.mappers.requests;

import com.georgeifrim.HibernateDemo.entities.Trainer;
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
        Trainer trainer = new Trainer();
        trainer.setTrainingType(trainingTypeRepo
                                    .findById(trainerRequestDto.getTraining_type_id())
                                    .orElseThrow(() -> new RuntimeException("Training type not found")));

        if (!userIsATrainee(trainerRequestDto.getUserId())) {
            trainer.setUser(userRepo.
                    findById(trainerRequestDto.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found")));
        }else
            throw new RuntimeException("User is already a trainee");

        return trainer;
    }

    @Override
    public TrainerRequestDto toRequestDto(Trainer trainer){
        TrainerRequestDto trainerRequestDto = new TrainerRequestDto();
        trainerRequestDto.setTraining_type_id(trainer.getTrainingType().getId());
        trainerRequestDto.setUserId(trainer.getUser().getId());
        return trainerRequestDto;
    }

    private boolean userIsATrainee(int userId) {
        return traineeRepo.findById(userId).isPresent();
    }

}
