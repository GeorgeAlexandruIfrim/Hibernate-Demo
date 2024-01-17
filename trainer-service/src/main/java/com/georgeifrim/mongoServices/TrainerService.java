package com.georgeifrim.mongoServices;

import com.georgeifrim.mappers.TrainerMapper;
import com.georgeifrim.mongoEntities.Trainer;
import com.georgeifrim.mongoRepositories.TrainerRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TrainerService {

    private final TrainerRepo trainerRepo;

    public TrainerMapper trainerFromMongo(String firstName){
        Trainer trainer = trainerRepo.findByUserFirstName(firstName).orElseThrow(() -> new IllegalArgumentException("No such trainer exists!"));
        TrainerMapper mapper = new TrainerMapper();

        TrainerMapper trainerMapper = mapper.toTrainerMapper(trainer);

        return trainerMapper;
    }

}
