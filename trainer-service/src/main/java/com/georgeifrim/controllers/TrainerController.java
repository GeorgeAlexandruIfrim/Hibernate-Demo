package com.georgeifrim.controllers;

import com.georgeifrim.entities.TrainerCompleteResponseDto;
import com.georgeifrim.entities.requests.Training;
import com.georgeifrim.entities.requests.TrainingWithHttpMethod;
import com.georgeifrim.jms.senders.Sender;
import com.georgeifrim.mappers.TrainerMapper;
import com.georgeifrim.mongoServices.TrainerService;
import com.georgeifrim.services.TrainerServices;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trainers")
@AllArgsConstructor
public class TrainerController {

    private final TrainerServices trainerServices;

    private final TrainerService mongoTrainerServices;

    private final Sender sender;

    @PutMapping("/{username}")
    public ResponseEntity<TrainerCompleteResponseDto> addTraining(@PathVariable String username,
                                                                  @RequestBody Training training){
    return trainerServices.addOrDeleteTrainingFromTrainer(username, training);

    }

    @GetMapping("/{username}")
    public ResponseEntity<TrainerMapper> trainer(@PathVariable String username){
        return trainerServices.trainer(username);
    }

    @PatchMapping
    public void sendMessage(@RequestBody TrainingWithHttpMethod training){
        sender.sendTraining(training);
    }

    @GetMapping
    public TrainerMapper trainerFromMongo(@RequestParam String firstName){
        return mongoTrainerServices.trainerFromMongo(firstName);
    }
}

