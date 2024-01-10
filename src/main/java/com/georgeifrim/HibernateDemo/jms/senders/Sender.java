package com.georgeifrim.HibernateDemo.jms.senders;

import com.georgeifrim.HibernateDemo.entities.dto.responses.TrainerCompleteResponseDto;
import com.georgeifrim.HibernateDemo.jms.listeners.Listener;
import com.georgeifrim.HibernateDemo.services.TrainerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Sender {

    private final TrainerService trainerService;

    private final Listener listener;

    public void sendTrainerResponse(TrainerCompleteResponseDto trainer){

    }
}
