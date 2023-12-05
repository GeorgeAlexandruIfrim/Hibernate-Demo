package com.georgeifrim.HibernateDemo.services;

import com.georgeifrim.HibernateDemo.entities.Trainer;
import com.georgeifrim.HibernateDemo.entities.dto.requests.TrainerRequestDto;
import com.georgeifrim.HibernateDemo.entities.dto.responses.TrainerResponseDto;
import com.georgeifrim.HibernateDemo.exceptions.trainingType.TrainingTypeDoesNotExist;
import com.georgeifrim.HibernateDemo.exceptions.users.UserWithUsernameAlreadyExists;
import com.georgeifrim.HibernateDemo.mappers.requests.RequestsMapper;
import com.georgeifrim.HibernateDemo.mappers.responses.ResponseMapper;
import com.georgeifrim.HibernateDemo.repositories.TrainerRepo;
import com.georgeifrim.HibernateDemo.repositories.TrainingTypeRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class TrainerService {

    private final TrainerRepo trainerRepo;
    private final UserService userService;

    private final TrainingTypeRepo trainingTypeRepo;

    private final RequestsMapper<Trainer, TrainerRequestDto> trainerRequestsMapper;
    private final ResponseMapper<Trainer, TrainerResponseDto> trainerResponseMapper;


    public TrainerResponseDto createTrainer(TrainerRequestDto trainerRequestDto) {
        String username = trainerRequestDto.trainerRequestDtoUsername();
        if(userService.userWithUsernameExists(username))
            throw new UserWithUsernameAlreadyExists(username);
        else if (!trainingTypeRepo.existsByName(trainerRequestDto.trainingTypeName()))
            throw new TrainingTypeDoesNotExist(trainerRequestDto.trainingTypeName());

        Trainer trainer = trainerRequestsMapper.toEntity(trainerRequestDto);
        log.info("Trainer " + username + " created");
        trainerRepo.save(trainer);
        return trainerResponseMapper.toResponseDto(trainer);
    }

    public Trainer getTrainerById(int id) {
        return trainerRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Trainer not found with id " + id));
    }

    public void deleteTrainer(int id) {
        trainerRepo.deleteById(id);
        log.info("Trainer with id " + id + " was deleted");
    }

    public Trainer getTrainerByUserName(String username) {
        return trainerRepo.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("User not found with username " + username));
    }

    public List<Trainer> activeTrainersWithNoTrainees() {
        return trainerRepo.findAllByUserIsActive(true)
                .stream()
                .filter(trainer -> trainer.getTrainees().isEmpty())
                .collect(Collectors.toList());
    }
}
