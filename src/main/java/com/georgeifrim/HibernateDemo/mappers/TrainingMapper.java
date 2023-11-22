package com.georgeifrim.HibernateDemo.mappers;

import com.georgeifrim.HibernateDemo.entities.Training;
import com.georgeifrim.HibernateDemo.entities.dto.TrainingDto;
import com.georgeifrim.HibernateDemo.exceptions.trainees.TraineeWithIdNotFound;
import com.georgeifrim.HibernateDemo.exceptions.trainer.TrainerWithIdNotFound;
import com.georgeifrim.HibernateDemo.repositories.TraineeRepo;
import com.georgeifrim.HibernateDemo.repositories.TrainerRepo;
import com.georgeifrim.HibernateDemo.repositories.TrainingTypeRepo;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
@AllArgsConstructor
public class TrainingMapper {

        private final TraineeRepo traineeRepo;
        private final TrainerRepo trainerRepo;

        private final TrainingTypeRepo trainingTypeRepo;

        public Training toDomain(TrainingDto trainingDto){
            Training training = new Training();

            training.setDate(trainingDto.getDate());
            training.setDuration_minutes(trainingDto.getDuration_minutes());
            training.setName(trainingDto.getName());
            training.setTrainee(traineeRepo.findById(trainingDto.getTrainee_id())
                                            .orElseThrow(() -> new TraineeWithIdNotFound(trainingDto.getTrainee_id())));
            training.setTrainer(trainerRepo.findById(trainingDto.getTrainer_id())
                                            .orElseThrow(() -> new TrainerWithIdNotFound(trainingDto.getTrainer_id())));
            training.setTrainingType(trainingTypeRepo.findById(trainingDto.getTraining_type_id())
                                            .orElseThrow(() -> new RuntimeException("Training type not found")));

            return training;
        }
}
