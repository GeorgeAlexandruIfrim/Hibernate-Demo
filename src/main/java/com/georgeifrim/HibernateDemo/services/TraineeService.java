package com.georgeifrim.HibernateDemo.services;

import com.georgeifrim.HibernateDemo.entities.Trainee;
import com.georgeifrim.HibernateDemo.entities.Training;
import com.georgeifrim.HibernateDemo.entities.dto.requests.TraineeRequestDto;
import com.georgeifrim.HibernateDemo.entities.dto.responses.TraineeCompleteResponseDto;
import com.georgeifrim.HibernateDemo.entities.dto.responses.TraineeResponseDto;
import com.georgeifrim.HibernateDemo.exceptions.trainees.TraineeWithIdNotFound;
import com.georgeifrim.HibernateDemo.exceptions.trainees.TraineeWithUsernameNotFound;
import com.georgeifrim.HibernateDemo.exceptions.trainer.TrainerWithIdNotFound;
import com.georgeifrim.HibernateDemo.exceptions.training.TrainingWithIdNotFound;
import com.georgeifrim.HibernateDemo.exceptions.users.UserWithUsernameAlreadyExists;
import com.georgeifrim.HibernateDemo.mappers.requests.TraineeRequestsMapper;
import com.georgeifrim.HibernateDemo.mappers.responses.TraineeCompleteResponseMapper;
import com.georgeifrim.HibernateDemo.mappers.responses.TraineeResponseMapper;
import com.georgeifrim.HibernateDemo.repositories.TraineeRepo;
import com.georgeifrim.HibernateDemo.repositories.TrainerRepo;
import com.georgeifrim.HibernateDemo.repositories.TrainingRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class TraineeService extends EntityService<Trainee, TraineeRequestDto, TraineeResponseDto> {

    private final TraineeRepo traineeRepo;
    private final TrainerRepo trainerRepo;
    private final UserService userService;
    private final TrainingRepo trainingRepo;
    private final TraineeRequestsMapper requestsMapper;
    private final TraineeResponseMapper responseMapper;
    private final TraineeCompleteResponseMapper traineeCompleteResponseMapper;

    @Transactional
    @Override
    public TraineeResponseDto create(TraineeRequestDto traineeRequestDto) {
        String username = traineeRequestDto.getUsername();

        if(userService.userWithUsernameExists(username)){
            throw new UserWithUsernameAlreadyExists(username);
        }

        Trainee traineeSaved = requestsMapper.toEntity(traineeRequestDto);
        Trainee traineeSaved1 = traineeRepo.save(traineeSaved);

        return responseMapper.toResponseDto(traineeSaved1);
    }

    public TraineeCompleteResponseDto getByUserName(String username) {

         Trainee trainee = traineeRepo.findByUserUsername(username)
                 .orElseThrow(() -> new TraineeWithUsernameNotFound(username));
         return traineeCompleteResponseMapper.toResponseDto(trainee);
    }

    @Transactional
    public TraineeCompleteResponseDto update(TraineeRequestDto traineeRequestDto) {
        String username = traineeRequestDto.getUsername();

        Trainee traineeToBeUpdated = traineeRepo
                .findByUserUsername(username)
                .orElseThrow(() -> new TraineeWithUsernameNotFound(username));

        traineeToBeUpdated.setAddress(traineeRequestDto.getAddress());
        traineeToBeUpdated.setDate_of_birth(traineeRequestDto.getDateOfBirth());
        traineeRepo.save(traineeToBeUpdated);
        log.info("Trainee with username " + username + " was updated");

        return traineeCompleteResponseMapper.toResponseDto(traineeToBeUpdated);
    }

    @Override
    @Transactional
    public Trainee updateActive(String username, boolean status) {
        Trainee traineeToBeUpdated = traineeRepo
                .findByUserUsername(username)
                .orElseThrow(() -> new TraineeWithUsernameNotFound(username));

        traineeToBeUpdated.getUser().setActive(status);

        log.info("Trainee with username " + username + " was updated");
        return traineeRepo.save(traineeToBeUpdated);
    }
    @Override
    public void delete(String username) {
        Trainee trainee = traineeRepo.findByUserUsername(username)
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

    @Transactional
    public void enrollInTraining(String username, int trainingId) {
        Trainee trainee = traineeRepo.findByUserUsername(username)
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
