package com.georgeifrim.HibernateDemo.controllers;

import com.georgeifrim.HibernateDemo.entities.Trainee;
import com.georgeifrim.HibernateDemo.entities.dto.TraineeDto;
import com.georgeifrim.HibernateDemo.services.TraineeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/trainees")
public class TraineeController {

    private final TraineeService traineeService;

    @GetMapping("/byId/{id}")
    public ResponseEntity<Trainee> getTraineeById(@PathVariable int id){

        return ResponseEntity.status(HttpStatus.OK)
                .body(traineeService.getTraineeById(id));
    }

    @GetMapping("/byUserName/{username}")
    public ResponseEntity<Trainee> getTraineeByUserName(@PathVariable String username){

        return ResponseEntity.status(HttpStatus.OK)
                .body(traineeService.getTraineeByUserName(username));
    }

    @PutMapping("/add")
    public ResponseEntity<Trainee> addTrainee(@RequestBody TraineeDto traineeDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(traineeService.createTrainee(traineeDto));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Trainee> updateTrainee(@PathVariable int id,
                                                 @RequestBody TraineeDto traineeDto){
        return ResponseEntity.status(HttpStatus.OK)
                .body(traineeService.updateTrainee(id, traineeDto));
    }
    @PostMapping("/updateStatus/{id}")
    public ResponseEntity<Trainee> updateActive(@RequestParam boolean status,
                                                @PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(traineeService.updateActive(id, status));
    }

    @PostMapping("/enrollTrainer/{traineeId}/{trainerId}")
    public void enrollTrainer(@PathVariable int traineeId,
                              @PathVariable int trainerId){
        traineeService.enrollTrainer(traineeId, trainerId);
    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity<Trainee> deleteTrainee(@PathVariable String username){
        return ResponseEntity.status(HttpStatus.OK)
                .body(traineeService.deleteTrainee(username));
    }


}
