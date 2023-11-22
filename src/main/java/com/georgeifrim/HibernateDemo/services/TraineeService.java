package com.georgeifrim.HibernateDemo.services;

import com.georgeifrim.HibernateDemo.entities.Trainee;
import com.georgeifrim.HibernateDemo.entities.Training;
import com.georgeifrim.HibernateDemo.entities.dto.TraineeDto;
import com.georgeifrim.HibernateDemo.exceptions.trainees.TraineeWithIdNotFound;
import com.georgeifrim.HibernateDemo.exceptions.trainees.TraineeWithUsernameNotFound;
import com.georgeifrim.HibernateDemo.exceptions.trainer.TrainerWithIdNotFound;
import com.georgeifrim.HibernateDemo.exceptions.training.TrainingWithIdNotFound;
import com.georgeifrim.HibernateDemo.mappers.TraineeMapper;
import com.georgeifrim.HibernateDemo.repositories.TraineeRepo;
import com.georgeifrim.HibernateDemo.repositories.TrainerRepo;
import com.georgeifrim.HibernateDemo.repositories.TrainingRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Log4j2
public class TraineeService {

    private final TraineeRepo traineeRepo;
    private final TraineeMapper traineeMapper;
    private final TrainerRepo trainerRepo;
    private final UserService userService;

    private final TrainingRepo trainingRepo;

    @Transactional
    public Trainee createTrainee(TraineeDto traineeDto) {
        var traineeToSave = traineeMapper.toDomain(traineeDto);
        log.info("Trainee created");
        return traineeRepo.save(traineeToSave);
    }

    public Trainee getTraineeById(Integer id) {
        return traineeRepo.findTraineeById(id)
                .orElseThrow(() -> new TraineeWithIdNotFound(id));
    }

    public Trainee getTraineeByUserName(String username) {

         return traineeRepo.findTraineeByUserName(username)
                 .orElseThrow(() -> new TraineeWithUsernameNotFound(username));
    }

    @Transactional
    public Trainee updateTrainee(int id, TraineeDto traineeDto) {
        if(!traineeWithIdExists(id)){
            throw new TraineeWithIdNotFound(id);
        }
        Trainee trainee = traineeRepo.findById(id).get();
        trainee.setDate_of_birth(traineeDto.getDate_of_birth());
        trainee.setAddress(traineeDto.getAddress());
        trainee.setUser(userService.getUserById(traineeDto.getUserId()));
        log.info("Trainee with id " + id + " was updated");
        return traineeRepo.save(trainee);
    }

    @Transactional
    public Trainee updateActive(int id, boolean status) {
        if(!traineeWithIdExists(id)){
            throw new TraineeWithIdNotFound(id);
        }
        Trainee existingTrainee = traineeRepo.findById(id).get();
        existingTrainee.getUser().setActive(status);
        log.info("Trainee with id " + id + " was updated");
        return traineeRepo.save(existingTrainee);
    }

    public void deleteTrainee(String username) {
        Trainee trainee = traineeRepo.findTraineeByUserName(username)
                .orElseThrow(() -> new TraineeWithUsernameNotFound(username));
        traineeRepo.delete(trainee);
        log.info("Trainee " + username + " was deleted");
    }

    @Transactional
    public void enrollTrainer(int traineeId, int trainerId) {
        Trainee trainee = traineeRepo.findById(traineeId)
                                        .orElseThrow(() -> new TraineeWithIdNotFound(traineeId));
        trainee.getTrainers()
                .add(trainerRepo.findById(trainerId)
                                .orElseThrow(() -> new TrainerWithIdNotFound(trainerId)));
        log.info("Trainer with id " + trainerId + " was enrolled to trainee with id " + traineeId);
        traineeRepo.save(trainee);
    }

    public boolean traineeWithIdExists(int id) {
        return traineeRepo.existsById(id);
    }

    @Transactional
    public void enrollInTraining(String username, int trainingId) {
        Trainee trainee = traineeRepo.findTraineeByUserName(username)
                .orElseThrow(() -> new TraineeWithUsernameNotFound(username));
        trainee.getTrainings()
                .add(trainingRepo.findById(trainingId)
                        .orElseThrow(() -> new TrainingWithIdNotFound(trainingId)));
        log.info("Trainee " + username + " was enrolled to training with id " + trainingId);
        traineeRepo.save(trainee);
    }

    public List<Training> getListOfTrainings(int traineeId) {
        Trainee trainee = traineeRepo.findById(traineeId)
                .orElseThrow(() -> new TraineeWithIdNotFound(traineeId));
        return trainee.getTrainings();
    }
}
