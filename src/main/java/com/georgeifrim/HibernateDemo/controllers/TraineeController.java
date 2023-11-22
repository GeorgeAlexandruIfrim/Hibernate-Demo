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
    public Trainee getTraineeById(@PathVariable Integer id){
        return traineeService.getTraineeById(id);
    }



    @GetMapping("/byUserName/{username}")
    public ResponseEntity<Trainee> getTraineeByUserName(@PathVariable String username){

        return ResponseEntity.status(HttpStatus.OK)
                .body(traineeService.getTraineeByUserName(username));
    }

    @GetMapping("/listOfTrainings/{traineeId}")
    public ResponseEntity<?> getListOfTrainings(@PathVariable int traineeId){
        return ResponseEntity.status(HttpStatus.OK)
                .body(traineeService.getListOfTrainings(traineeId));
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

    @PostMapping("/enrollInTraining/{username}/{trainingId}")
    public void enrollInTraining(@PathVariable String username,
                                 @PathVariable int trainingId){
        traineeService.enrollInTraining(username, trainingId);
    }

    @DeleteMapping("/delete/{username}")
    public void deleteTrainee(@PathVariable String username){
        traineeService.deleteTrainee(username);
    }


}
