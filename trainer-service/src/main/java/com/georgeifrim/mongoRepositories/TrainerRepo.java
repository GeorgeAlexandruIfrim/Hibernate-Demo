package com.georgeifrim.mongoRepositories;

import com.georgeifrim.mongoEntities.Trainer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TrainerRepo extends MongoRepository<Trainer, String> {

    Optional<Trainer> findByUserFirstName(String firstName);
}
