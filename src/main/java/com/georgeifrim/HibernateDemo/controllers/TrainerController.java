package com.georgeifrim.HibernateDemo.controllers;

import com.georgeifrim.HibernateDemo.entities.Trainer;
import com.georgeifrim.HibernateDemo.entities.dto.requests.TrainerRequestDto;
import com.georgeifrim.HibernateDemo.entities.dto.responses.TrainerCompleteResponseDto;
import com.georgeifrim.HibernateDemo.entities.dto.responses.TrainerResponseDto;
import com.georgeifrim.HibernateDemo.services.TrainerService;
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

    @PutMapping()
    public ResponseEntity<TrainerResponseDto> createTrainer(@RequestBody TrainerRequestDto trainerRequestDto) {
       return ResponseEntity.status(HttpStatus.OK)
                            .body(trainerService.createTrainer(trainerRequestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trainer> getTrainerById(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(trainerService.getTrainerById(id));
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
    @GetMapping()
    public List<TrainerCompleteResponseDto> activeOrNotWithCertainNumberOfTrainees(
            @RequestParam(required = false, defaultValue = "0") int numberOfTrainees,
            @RequestParam(required = false, defaultValue = "true") boolean activeStatus
    ){
        return trainerService.activeTrainersWithNoTrainees(numberOfTrainees, activeStatus);
    }

    @DeleteMapping("/{id}")
    public void deleteTrainer(@PathVariable int id){
        trainerService.deleteTrainer(id);
    }



}
