package com.georgeifrim.HibernateDemo.mappers.requests;

import com.georgeifrim.HibernateDemo.entities.Training;
import com.georgeifrim.HibernateDemo.entities.dto.requests.TrainingRequestDto;
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
public class TrainingRequestsMapper implements RequestsMapper<Training, TrainingRequestDto> {

        private final TraineeRepo traineeRepo;
        private final TrainerRepo trainerRepo;

        private final TrainingTypeRepo trainingTypeRepo;

        @Override
        public Training toEntity(TrainingRequestDto trainingRequestDto){
            Training training = new Training();

            training.setDate(trainingRequestDto.getDate());
            training.setDuration_minutes(trainingRequestDto.getDuration_minutes());
            training.setName(trainingRequestDto.getName());
            training.setTrainee(traineeRepo.findById(trainingRequestDto.getTrainee_id())
                                            .orElseThrow(() -> new TraineeWithIdNotFound(trainingRequestDto.getTrainee_id())));
            training.setTrainer(trainerRepo.findById(trainingRequestDto.getTrainer_id())
                                            .orElseThrow(() -> new TrainerWithIdNotFound(trainingRequestDto.getTrainer_id())));
            training.setTrainingType(trainingTypeRepo.findById(trainingRequestDto.getTraining_type_id())
                                            .orElseThrow(() -> new RuntimeException("Training type not found")));

            return training;
        }

    @Override
    public TrainingRequestDto toRequestDto(Training training) {
        return null;
    }
}
