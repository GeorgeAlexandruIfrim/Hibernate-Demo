package com.georgeifrim.HibernateDemo.mappers;

import com.georgeifrim.HibernateDemo.entities.Trainer;
import com.georgeifrim.HibernateDemo.entities.dto.TrainerDto;
import com.georgeifrim.HibernateDemo.repositories.TraineeRepo;
import com.georgeifrim.HibernateDemo.repositories.TrainingTypeRepo;
import com.georgeifrim.HibernateDemo.repositories.UserRepo;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
@AllArgsConstructor
public class TrainerMapper {


    private final TrainingTypeRepo trainingTypeRepo;
    private final TraineeRepo traineeRepo;
    private final UserRepo userRepo;

    public Trainer toDomain(TrainerDto trainerDto){
        Trainer trainer = new Trainer();
        trainer.setTrainingType(trainingTypeRepo
                                    .findById(trainerDto.getTraining_type_id())
                                    .orElseThrow(() -> new RuntimeException("Training type not found")));

        if (!userIsATrainee(trainerDto.getUserId())) {
            trainer.setUser(userRepo.
                    findById(trainerDto.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found")));
        }else
            throw new RuntimeException("User is already a trainee");

        return trainer;
    }

    public TrainerDto toDto(Trainer trainer){
        TrainerDto trainerDto = new TrainerDto();
        trainerDto.setTraining_type_id(trainer.getTrainingType().getId());
        trainerDto.setUserId(trainer.getUser().getId());
        return trainerDto;
    }

    private boolean userIsATrainee(int userId) {
        return traineeRepo.findById(userId).isPresent();
    }

}
