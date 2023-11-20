package com.georgeifrim.HibernateDemo.services;

import com.georgeifrim.HibernateDemo.entities.Trainee;
import com.georgeifrim.HibernateDemo.entities.dto.TraineeDto;
import com.georgeifrim.HibernateDemo.mappers.TraineeMapper;
import com.georgeifrim.HibernateDemo.repositories.TraineeRepo;
import com.georgeifrim.HibernateDemo.repositories.TrainerRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Log4j2
public class TraineeService {

    private final TraineeRepo traineeRepo;
    private final TraineeMapper traineeMapper;
    private final TrainerRepo trainerRepo;

    @Transactional
    public Trainee createTrainee(TraineeDto traineeDto) {
        var traineeToSave = traineeMapper.toDomain(traineeDto);
        log.info("Trainee created");
        return traineeRepo.save(traineeToSave);
    }

    public Trainee getTraineeById(int id) {
        return traineeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Trainee not found"));
    }

    public Trainee getTraineeByUserName(String username) {

         return traineeRepo.findTraineeByUserName(username)
                 .orElseThrow(() -> new RuntimeException("Trainee not found"));
    }

    public Trainee updateTrainee(int id, TraineeDto traineeDto) {
        if(!traineeWithIdExists(id)){
            throw new RuntimeException("Trainee with id " + id + " does not exist");
        }
        Trainee trainee = traineeMapper.toDomain(traineeDto);
        log.info("Trainee with id " + id + " was updated");

        return traineeRepo.save(trainee);
    }

    public Trainee updateActive(int id, boolean status) {
        if(!traineeWithIdExists(id)){
            throw new RuntimeException("Trainee with id " + id + " does not exist");
        }
        log.info("Trainee with id " + id + " was updated");
        Trainee existingTrainee = traineeRepo.findById(id).get();
        existingTrainee.getUser().setActive(status);
        return traineeRepo.save(existingTrainee);
    }

    public Trainee deleteTrainee(String username) {
        Trainee trainee = traineeRepo.findTraineeByUserName(username)
                .orElseThrow(() -> new RuntimeException("Trainee " + username + " not found"));
        traineeRepo.delete(trainee);
        log.info("Trainee " + username + " was deleted");
        return trainee;
    }

    public void enrollTrainer(int traineeId, int trainerId) {
        Trainee trainee = traineeRepo.findById(traineeId)
                                        .orElseThrow(() -> new RuntimeException("Trainee with id " + traineeId + " not found"));
        trainee.getTrainers()
                .add(trainerRepo.findById(trainerId)
                                .orElseThrow(() -> new RuntimeException("Trainer with id " + trainerId + " not found")));
        traineeRepo.save(trainee);
    }

    private boolean traineeWithIdExists(int id) {
        return traineeRepo.existsById(id);
    }
}
