package com.georgeifrim.HibernateDemo.jms.listeners;

import com.georgeifrim.HibernateDemo.entities.dto.requests.TrainingRequestDto;
import com.georgeifrim.entities.requests.TrainingWithHttpMethod;
import com.georgeifrim.HibernateDemo.exceptions.trainer.TrainerWithIdNotFound;
import com.georgeifrim.HibernateDemo.repositories.TrainerRepo;
import com.georgeifrim.HibernateDemo.services.TrainerService;
import com.georgeifrim.HibernateDemo.services.TrainingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class Listener {

    private final TrainingService trainingService;
    private final TrainerService trainerService;
    private final TrainerRepo trainerRepo;
    @JmsListener(destination = "trainer-queue-request")
    public void listen(TrainingWithHttpMethod training){
        int trainerId = training.getTrainer_id();
        String trainerUsername = trainerRepo.findById(trainerId).orElseThrow(() -> new TrainerWithIdNotFound(trainerId)).getUser().getUsername();

        String httpMethod = training.getHttpMethod();
        TrainingRequestDto trainingToBeSaved = new TrainingRequestDto(training);

        if(httpMethod.equals("PUT")){
            trainingService.createTraining(trainingToBeSaved);
        } else if (httpMethod.equals("DELETE")) {
            trainerService.deleteTrainingFromTrainer(trainingToBeSaved, trainerUsername);
        }else {
            throw new RuntimeException("No such method supported");
        }
    }
}
