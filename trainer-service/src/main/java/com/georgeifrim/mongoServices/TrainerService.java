package com.georgeifrim.mongoServices;

import com.georgeifrim.mappers.TrainerMapper;
import com.georgeifrim.mongoEntities.Trainer;
import com.georgeifrim.mongoRepositories.TrainerRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class TrainerService {

    private final TrainerRepo trainerRepo;

    private final MongoTemplate mongoTemplate;

    public TrainerMapper trainerFromMongo(String firstName){
        Trainer trainer = trainerRepo.findByUserFirstName(firstName).orElseThrow(() -> new IllegalArgumentException("No such trainer exists!"));
        TrainerMapper mapper = new TrainerMapper();

        TrainerMapper trainerMapper = mapper.toTrainerMapper(trainer);

        return trainerMapper;
    }

    @Transactional
    public Trainer addMongoTrainer(Trainer trainer){
        return mongoTemplate.insert(trainer);
    }

}
