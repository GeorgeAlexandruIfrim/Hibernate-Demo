package com.georgeifrim.HibernateDemo.controllers;

import com.georgeifrim.HibernateDemo.entities.Trainee;
import com.georgeifrim.HibernateDemo.entities.dto.requests.TraineeRequestDto;
import com.georgeifrim.HibernateDemo.entities.dto.responses.TraineeCompleteResponseDto;
import com.georgeifrim.HibernateDemo.entities.dto.responses.TraineeResponseDto;
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
        return traineeService.getById(id);
    }



    @GetMapping("/byUserName/{username}")
    public ResponseEntity<TraineeCompleteResponseDto> getTraineeByUserName(@PathVariable String username){

        return ResponseEntity.status(HttpStatus.OK)
                .body(traineeService.getByUserName(username));
    }

    @GetMapping("/listOfTrainings/{traineeId}")
    public ResponseEntity<?> getListOfTrainings(@PathVariable int traineeId){
        return ResponseEntity.status(HttpStatus.OK)
                .body(traineeService.getListOfTrainings(traineeId));
    }

    @PutMapping("/add")
    public ResponseEntity<TraineeResponseDto> addTrainee(@RequestBody TraineeRequestDto traineeRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(traineeService.create(traineeRequestDto));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Trainee> updateTrainee(@PathVariable int id,
                                                 @RequestBody TraineeRequestDto traineeRequestDto){
        return ResponseEntity.status(HttpStatus.OK)
                .body(traineeService.update(id, traineeRequestDto));
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
        traineeService.delete(username);
    }


}
