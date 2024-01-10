package com.georgeifrim.jms.senders;

import lombok.AllArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Sender {

    private static final String TRAINER_QUEUE = "trainer-queue-request";

    private final JmsTemplate jmsTemplate;
    public void sendUsername(String username){
            jmsTemplate.convertAndSend(TRAINER_QUEUE, username);
    }
}
