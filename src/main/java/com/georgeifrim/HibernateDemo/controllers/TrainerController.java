package com.georgeifrim.HibernateDemo.controllers;

import com.georgeifrim.HibernateDemo.entities.Trainer;
import com.georgeifrim.HibernateDemo.entities.dto.requests.TrainerRequestDto;
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

    @PutMapping("/add")
    public ResponseEntity<TrainerResponseDto> createTrainer(@RequestBody TrainerRequestDto trainerRequestDto) {
       return ResponseEntity.status(HttpStatus.OK)
                            .body(trainerService.createTrainer(trainerRequestDto));
    }

    @GetMapping("/byId/{id}")
    public ResponseEntity<Trainer> getTrainerById(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(trainerService.getTrainerById(id));
    }
    @GetMapping("/byUserName/{username}")
    public ResponseEntity<Trainer> getTrainerByUserName(@PathVariable String username){

        return ResponseEntity.status(HttpStatus.OK)
                .body(trainerService.getTrainerByUserName(username));
    }
    @GetMapping("/activeTrainersWithNoTrainees")
    public List<Trainer> activeTrainersWithNoTrainees(){
        return trainerService.activeTrainersWithNoTrainees();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTrainer(@PathVariable int id){
        trainerService.deleteTrainer(id);
    }



}
