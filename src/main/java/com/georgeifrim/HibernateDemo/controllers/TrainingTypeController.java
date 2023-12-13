package com.georgeifrim.HibernateDemo.controllers;

import com.georgeifrim.HibernateDemo.entities.TrainingType;
import com.georgeifrim.HibernateDemo.repositories.TrainingTypeRepo;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trainingTypes")
@AllArgsConstructor
public class TrainingTypeController {

    private final TrainingTypeRepo trainingTypeRepo;

    @GetMapping()
    public TrainingType byName(@RequestParam String name){

        return trainingTypeRepo.findByName(name);

    }
}
