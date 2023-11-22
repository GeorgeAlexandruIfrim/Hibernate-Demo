package com.georgeifrim.HibernateDemo.controllers;

import com.georgeifrim.HibernateDemo.entities.Training;
import com.georgeifrim.HibernateDemo.entities.dto.TrainingDto;
import com.georgeifrim.HibernateDemo.services.TrainingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/trainings")
public class TrainingController {

    private final TrainingService trainingService;

    @PutMapping("/add")
    public ResponseEntity<Training> createTraining(@RequestBody TrainingDto trainingDto) {
       return ResponseEntity.status(HttpStatus.OK)
                            .body(trainingService.createTraining(trainingDto));
    }

    @GetMapping("byId/{id}")
    public ResponseEntity<Training> getTrainingById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(trainingService.getTrainingById(id));
    }
}
