package com.georgeifrim.HibernateDemo.jms.listeners;

import com.georgeifrim.HibernateDemo.entities.dto.responses.TrainerCompleteResponseDto;
import com.georgeifrim.HibernateDemo.services.TrainerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class Listener {

    private final TrainerService trainerService;
    @JmsListener(destination = "order-queue-request")
    public String listen(String username){
        return username;
    }
}
