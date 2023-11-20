package com.georgeifrim.HibernateDemo.controllers;

import com.georgeifrim.HibernateDemo.entities.Trainer;
import com.georgeifrim.HibernateDemo.services.TrainerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/trainers")
public class TrainerController {


    private final TrainerService trainerService;

    @PutMapping("/add")
    public ResponseEntity<Trainer> createTrainer(@RequestParam int userId,
                                                 @RequestParam int trainingTypeId,
                                                 @RequestBody Trainer trainer) {
       return ResponseEntity.status(HttpStatus.OK)
                            .body(trainerService.createTrainer(userId, trainingTypeId, trainer));
//        trainerService.createTrainer(userId, trainingTypeId);
    }

    @GetMapping("/byId/{id}")
    public ResponseEntity<Trainer> getTrainerById(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(trainerService.getTrainerById(id));
    }

}
