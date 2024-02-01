package com.georgeifrim.mappers;

import com.georgeifrim.entities.TrainerCompleteResponseDto;
import com.georgeifrim.entities.responses.TrainingResponse;
import com.georgeifrim.mongoEntities.Trainer;
import com.georgeifrim.mongoEntities.Trainings;
import lombok.Data;

import java.util.Map;
import java.util.stream.Collectors;

@Data
public class TrainerMapper {

    private String username;
    private Map<Integer, Map<String, Integer>> activity;

    public TrainerMapper toTrainerMapper(TrainerCompleteResponseDto trainer){

        this.username = trainer.getUsername();

        this.activity = trainer
                .getTrainings()
                .stream()
                .collect(Collectors.groupingBy(TrainingResponse::getYear,
                            Collectors.groupingBy(TrainingResponse::getMonth,
                                    Collectors.summingInt(TrainingResponse::getDurationMinutes))));
        return this;
    }
    public TrainerMapper toTrainerMapper(Trainer trainer){

        this.username = trainer.getUser().getFirstName() + " " + trainer.getUser().getLastName();

        this.activity = trainer
                .getTrainings()
                .stream()
                .collect(Collectors.groupingBy(t -> t.getDate().getYear(),
                        Collectors.groupingBy(t -> t.getDate().getMonth().toString(),
                                Collectors.summingInt(Trainings::getDuration_minutes))));
        return this;
    }

}
