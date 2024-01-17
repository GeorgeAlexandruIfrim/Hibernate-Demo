package com.georgeifrim;

import com.georgeifrim.mongoEntities.Trainer;
import com.georgeifrim.mongoEntities.Trainings;
import com.georgeifrim.mongoEntities.User;
import com.georgeifrim.mongoRepositories.TrainerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
@EnableJms
public class TrainerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrainerServiceApplication.class, args);}
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    CommandLineRunner runner(TrainerRepo trainerRepo){
        return args -> {

            User user = new User("Ronnie", "O'Sullivan");
            Trainings training1 = new Trainings(
                    LocalDate.of(2023,12,12),
                    35,
                    "Strength"
            );            Trainings training2 = new Trainings(
                    LocalDate.of(2023,12,22),
                    65,
                    "Cardio"
            );            Trainings training3 = new Trainings(
                    LocalDate.of(2023,11,11),
                    55,
                    "Strength"
            );            Trainings training4 = new Trainings(
                    LocalDate.of(2024,1,12),
                    45,
                    "Strength"
            );

            Trainer trainer = new Trainer();
            trainer.setUser(user);
            trainer.setTrainings(List.of(training1, training2, training3, training4));

            trainerRepo.insert(trainer);
        };
    }
}
