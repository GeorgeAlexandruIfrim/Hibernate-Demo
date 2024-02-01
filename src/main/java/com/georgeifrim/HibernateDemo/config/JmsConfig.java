package com.georgeifrim.HibernateDemo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.jms.ConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@Configuration
@EnableJms
public class JmsConfig {

    @Bean
    public ActiveMQConnectionFactory connectionFactory(){
        return new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");
    }

//    @Bean
//    public JmsTemplate jmsTemplate(){
//        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory());
//        jmsTemplate.setConnectionFactory(connectionFactory());
//        jmsTemplate.setMessageConverter(jacksonMessageConverter());
//        return jmsTemplate;
//    }

    @Bean
    public JmsListenerContainerFactory listenerContainerFactory(ConnectionFactory connectionFactory,
                                                                DefaultJmsListenerContainerFactoryConfigurer configurer){
        var containerFactory = new DefaultJmsListenerContainerFactory();
        configurer.configure(containerFactory, connectionFactory);
        return containerFactory;
    }

    @Bean
    public DefaultJmsListenerContainerFactory defaultJmsListenerContainerFactory(){
        var factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setConcurrency("1-1");
        return factory;
    }

    @Bean
    public MessageConverter jacksonMessageConverter(){
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        converter.setObjectMapper(objectMapper);
        return converter;
    }

}
