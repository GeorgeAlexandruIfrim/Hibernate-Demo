package com.georgeifrim.HibernateDemo.services;

import com.georgeifrim.HibernateDemo.entities.Trainer;
import com.georgeifrim.HibernateDemo.entities.TrainingType;
import com.georgeifrim.HibernateDemo.repositories.TrainerRepo;
import com.georgeifrim.HibernateDemo.repositories.TrainingTypeRepo;
import com.georgeifrim.HibernateDemo.repositories.UserRepo;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Log4j2
public class TrainerService {

    private final TrainerRepo trainerRepo;

    private final UserRepo userRepo;

    private final TrainingTypeRepo trainingTypeRepo;


    public Trainer createTrainer(int userId, int trainingTypeId, Trainer trainer) {

        trainer.setUser(userRepo.findById(userId)
                                .orElseThrow(() -> new RuntimeException("User not found with id " + userId)));

        trainer.setTrainingType(trainingTypeRepo.findById(trainingTypeId)
                                                .orElseThrow(() -> new RuntimeException("Training type not found with id " + trainingTypeId)));

        log.info("Trainer created");
        return trainerRepo.save(trainer);
    }
}
