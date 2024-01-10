package com.georgeifrim.HibernateDemo.services;

import com.georgeifrim.HibernateDemo.entities.Trainer;
import com.georgeifrim.HibernateDemo.entities.Training;
import com.georgeifrim.HibernateDemo.entities.TrainingType;
import com.georgeifrim.HibernateDemo.entities.dto.requests.TrainerRequestDto;
import com.georgeifrim.HibernateDemo.entities.dto.requests.TrainingRequestDto;
import com.georgeifrim.HibernateDemo.entities.dto.responses.TrainerCompleteResponseDto;
import com.georgeifrim.HibernateDemo.entities.dto.responses.TrainerResponseDto;
import com.georgeifrim.HibernateDemo.exceptions.trainer.TrainerWithIdNotFound;
import com.georgeifrim.HibernateDemo.exceptions.trainer.TrainerWithUsernameNotFound;
import com.georgeifrim.HibernateDemo.exceptions.trainingType.TrainingTypeDoesNotExist;
import com.georgeifrim.HibernateDemo.exceptions.users.UserWithUsernameAlreadyExists;
import com.georgeifrim.HibernateDemo.mappers.requests.RequestsMapper;
import com.georgeifrim.HibernateDemo.mappers.responses.ResponseMapper;
import com.georgeifrim.HibernateDemo.mappers.responses.TrainerCompleteResponseMapper;
import com.georgeifrim.HibernateDemo.repositories.TrainerRepo;
import com.georgeifrim.HibernateDemo.repositories.TrainingRepo;
import com.georgeifrim.HibernateDemo.repositories.TrainingTypeRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class TrainerService {

    private final TrainerRepo trainerRepo;

    private final TrainingRepo trainingRepo;

    private final UserService userService;

    private final TrainingTypeRepo trainingTypeRepo;

    private final TrainerCompleteResponseMapper trainerCompleteResponseMapper;

    private final RequestsMapper<Trainer, TrainerRequestDto> trainerRequestsMapper;
    private final ResponseMapper<Trainer, TrainerResponseDto> trainerResponseMapper;

    public TrainerResponseDto createTrainer(TrainerRequestDto trainerRequestDto) {
        String username = trainerRequestDto.getUsername();
        if(userService.userWithUsernameExists(username))
            throw new UserWithUsernameAlreadyExists(username);
        else if (!trainingTypeRepo.existsByName(trainerRequestDto.getTrainingTypeName()))
            throw new TrainingTypeDoesNotExist(trainerRequestDto.getTrainingTypeName());

        Trainer trainer = trainerRequestsMapper.toEntity(trainerRequestDto);
        log.info("Trainer " + username + " created");
        trainerRepo.save(trainer);
        return trainerResponseMapper.toResponseDto(trainer);
    }

    public void deleteTrainer(String username) {
        Trainer trainer = trainerRepo.findByUserUsername(username).orElseThrow(() -> new TrainerWithUsernameNotFound(username));
        trainerRepo.deleteById(trainer.getId());
        log.info("Trainer with username " + username +" was deleted");
    }

    public TrainerCompleteResponseDto getTrainerByUserName(String username) {
        Trainer existingTrainer = trainerRepo.findByUserUsername(username)
                .orElseThrow(() -> new RuntimeException("Trainer with username " + username + " not found"));
        return trainerCompleteResponseMapper.toResponseDto(existingTrainer);
    }

    public List<TrainerCompleteResponseDto> activeTrainersWithNoTrainees(int numberOfTrainees, boolean activeStatus) {
        return trainerRepo.findAllByUserIsActive(activeStatus)
                .stream()
                .filter(trainer -> trainer.getTrainees().size() == numberOfTrainees)
                .map(trainerCompleteResponseMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public TrainerCompleteResponseDto updateTrainer(TrainerRequestDto trainerRequestDto) {
        TrainingType trainingType = trainingTypeRepo.findByName(trainerRequestDto.getTrainingTypeName());
        String username = trainerRequestDto.getUsername();
        Trainer toBeUpdated = trainerRepo.findByUserUsername(username)
                .orElseThrow(() -> new TrainerWithUsernameNotFound(username));
        toBeUpdated.setTrainingType(trainingType);
        var trainerSaved = trainerRepo.save(toBeUpdated);
        log.info("Trainer " + username + " was updated!");
        return trainerCompleteResponseMapper.toResponseDto(trainerSaved);
    }
    @Transactional
    public void deleteTrainingFromTrainer(TrainingRequestDto training, String username){
        Integer trainerId = training.getTrainer_id();
        String trainerUsername = trainerRepo.findById(trainerId).orElseThrow(() -> new TrainerWithIdNotFound(trainerId)).getUser().getUsername();
        if(!username.equals(trainerUsername)){
            throw new RuntimeException("The training doesn't belong to the trainer with username " + username);
        }else{
            trainingRepo.deleteTrainingByName(training.getName());
        }
    }
}
