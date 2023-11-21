package com.georgeifrim.HibernateDemo.services;

import com.georgeifrim.HibernateDemo.entities.Trainer;
import com.georgeifrim.HibernateDemo.entities.TrainingType;
import com.georgeifrim.HibernateDemo.entities.dto.TrainerDto;
import com.georgeifrim.HibernateDemo.mappers.TrainerMapper;
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

    private final TrainerMapper trainerMapper;


    public Trainer createTrainer(TrainerDto trainerdto) {

        Trainer trainer = trainerMapper.toDomain(trainerdto);

        log.info("Trainer created");
        return trainerRepo.save(trainer);
    }

    public Trainer getTrainerById(int id) {
        return trainerRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Trainer not found with id " + id));
    }

    public void deleteTrainer(int id) {
        trainerRepo.deleteById(id);
        log.info("Trainer with id " + id + " was deleted");
    }
}
