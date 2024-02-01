package com.georgeifrim.HibernateDemo.controllers;

import com.georgeifrim.HibernateDemo.entities.Trainer;
import com.georgeifrim.HibernateDemo.entities.dto.requests.TrainerRequestDto;
import com.georgeifrim.HibernateDemo.entities.dto.requests.TrainingRequestDto;
import com.georgeifrim.HibernateDemo.entities.dto.responses.TrainerCompleteResponseDto;
import com.georgeifrim.HibernateDemo.entities.dto.responses.TrainerResponseDto;
import com.georgeifrim.HibernateDemo.exceptions.trainer.TrainerWithUsernameNotFound;
import com.georgeifrim.HibernateDemo.repositories.TrainerRepo;
import com.georgeifrim.HibernateDemo.services.TrainerService;
import com.georgeifrim.HibernateDemo.services.TrainingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/trainers")
public class TrainerController {


    private final TrainerService trainerService;

    private final TrainerRepo trainerRepo;

    private final TrainingService trainingService;

    @PutMapping()
    public ResponseEntity<TrainerResponseDto> createTrainer(@RequestBody TrainerRequestDto trainerRequestDto) {
       return ResponseEntity.status(HttpStatus.OK)
                            .body(trainerService.createTrainer(trainerRequestDto));
    }

    @GetMapping("/{username}")
    public ResponseEntity<TrainerCompleteResponseDto> getTrainerByUserName(@PathVariable String username){

        return ResponseEntity.status(HttpStatus.OK)
                .body(trainerService.getTrainerByUserName(username));
    }
    @PutMapping("/update")
    public ResponseEntity<TrainerCompleteResponseDto> update(@RequestBody TrainerRequestDto trainerRequestDto){
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(trainerService.updateTrainer(trainerRequestDto));
    }
    @GetMapping("/search")
    public List<TrainerCompleteResponseDto> activeOrNotWithCertainNumberOfTrainees(
            @RequestParam(required = false, defaultValue = "0") int numberOfTrainees,
            @RequestParam(required = false, defaultValue = "true") boolean activeStatus
    ){
        return trainerService.activeTrainersWithNoTrainees(numberOfTrainees, activeStatus);
    }

    @PutMapping("/{username}")
    public void addTrainingToTrainer(@PathVariable String username,
                                     @RequestBody TrainingRequestDto training){
        Integer trainerId = trainerRepo.findByUserUsername(username).orElseThrow(() -> new TrainerWithUsernameNotFound(username)).getId();
        training.setTrainer_id(trainerId);
        trainingService.createTraining(training);
    }

    @DeleteMapping("/{username}")
    public void deleteTrainingFromTrainer(@PathVariable String username,
                              @RequestBody TrainingRequestDto training){
        trainerService.deleteTrainingFromTrainer(training, username);

    }



}
