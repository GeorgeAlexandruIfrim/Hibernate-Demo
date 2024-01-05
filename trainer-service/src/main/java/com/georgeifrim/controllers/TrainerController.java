package com.georgeifrim.controllers;

import com.georgeifrim.entities.TrainerCompleteResponseDto;
import com.georgeifrim.entities.requests.Training;
import com.georgeifrim.services.TrainerServices;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trainers")
@AllArgsConstructor
public class TrainerController {

    private final TrainerServices trainerServices;

    @GetMapping("/{username}")
    public ResponseEntity<TrainerCompleteResponseDto> addTraining(@PathVariable String username){

    return trainerServices.addTraining(username);

    }

}
