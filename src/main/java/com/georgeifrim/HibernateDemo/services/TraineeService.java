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
import com.georgeifrim.HibernateDemo.mappers.responses.TraineeCompleteResponseMapper;
import com.georgeifrim.HibernateDemo.repositories.TraineeRepo;
import com.georgeifrim.HibernateDemo.repositories.TrainerRepo;
import com.georgeifrim.HibernateDemo.repositories.TrainingRepo;
import com.georgeifrim.HibernateDemo.repositories.UserRepo;
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
    private final UserRepo userRepo;
    private final TrainingRepo trainingRepo;

    private final TraineeCompleteResponseMapper traineeCompleteResponseMapper;
    @Transactional
    @Override
    public TraineeResponseDto create(TraineeRequestDto traineeRequestDto) {
        String username = traineeRequestDto.traineeRequestDtoUsername();
        if(userService.userWithUsernameExists(username))
            throw new UserWithUsernameAlreadyExists(username);

        var traineeSaved = requestsMapper.toEntity(traineeRequestDto);
        traineeRepo.save(traineeSaved);
        return responseMapper.toResponseDto(traineeSaved);

    }

    @Override
    public Trainee getById(Integer id) {
        return traineeRepo.findTraineeById(id)
                .orElseThrow(() -> new TraineeWithIdNotFound(id));
    }

    public TraineeCompleteResponseDto getByUserName(String username) {

         Trainee trainee = traineeRepo.findTraineeByUserName(username)
                 .orElseThrow(() -> new TraineeWithUsernameNotFound(username));
         return  traineeCompleteResponseMapper.toResponseDto(trainee);
    }

    @Override
    @Transactional
    public Trainee update(Integer id, TraineeRequestDto traineeRequestDto) {
        if(!traineeWithIdExists(id)){
            throw new TraineeWithIdNotFound(id);
        }
        Trainee trainee = traineeRepo.findById(id).get();
        trainee.setDate_of_birth(traineeRequestDto.date_of_birth());
        trainee.setAddress(traineeRequestDto.address());
        trainee.setUser(userRepo.findByUsername(traineeRequestDto.traineeRequestDtoUsername()));
        log.info("Trainee with id " + id + " was updated");
        return traineeRepo.save(trainee);
    }

    @Override
    @Transactional
    public Trainee updateActive(Integer id, boolean status) {
        if(!traineeWithIdExists(id)){
            throw new TraineeWithIdNotFound(id);
        }
        Trainee existingTrainee = traineeRepo.findById(id).get();
        existingTrainee.getUser().setActive(status);
        log.info("Trainee with id " + id + " was updated");
        return traineeRepo.save(existingTrainee);
    }
    @Override
    public void delete(String username) {
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
