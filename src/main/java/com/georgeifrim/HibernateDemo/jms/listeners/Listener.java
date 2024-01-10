package com.georgeifrim.HibernateDemo.jms.listeners;

import com.georgeifrim.HibernateDemo.entities.dto.requests.TrainingRequestDto;
import com.georgeifrim.HibernateDemo.entities.dto.requests.TrainingRequestWithHttpMethod;
import com.georgeifrim.HibernateDemo.exceptions.trainer.TrainerWithIdNotFound;
import com.georgeifrim.HibernateDemo.exceptions.trainer.TrainerWithUsernameNotFound;
import com.georgeifrim.HibernateDemo.repositories.TrainerRepo;
import com.georgeifrim.HibernateDemo.services.TrainerService;
import com.georgeifrim.HibernateDemo.services.TrainingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class Listener {

    private final TrainingService trainingService;
    private final TrainerService trainerService;
    private final TrainerRepo trainerRepo;
    @JmsListener(destination = "order-queue-request")
    public void listen(TrainingRequestWithHttpMethod training){
        int trainerId = training.getTrainer_id();
        String trainerUsername = trainerRepo.findById(trainerId).orElseThrow(() -> new TrainerWithIdNotFound(trainerId)).getUser().getUsername();

        HttpMethod httpMethod = training.getHttpMethod();
        TrainingRequestDto trainingToBeSaved = new TrainingRequestDto(training);

        if(httpMethod.equals(HttpMethod.PUT)){
            trainingService.createTraining(trainingToBeSaved);
        } else if (httpMethod.equals(HttpMethod.DELETE)) {
            trainerService.deleteTrainingFromTrainer(trainingToBeSaved, trainerUsername);
        }else {
            throw new RuntimeException("No such method supported");
        }
    }
}
