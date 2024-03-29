package com.georgeifrim.jms.senders;

import com.georgeifrim.entities.requests.Training;
import com.georgeifrim.entities.requests.TrainingWithHttpMethod;
import lombok.AllArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Sender {

    private static final String TRAINER_QUEUE = "trainer-queue-request";

    private final JmsTemplate jmsTemplate;
    public void sendTraining(TrainingWithHttpMethod training){

            jmsTemplate.convertAndSend(TRAINER_QUEUE, training);
    }
}
