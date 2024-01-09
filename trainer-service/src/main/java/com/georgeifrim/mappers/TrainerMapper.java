package com.georgeifrim.mappers;

import com.georgeifrim.entities.TrainerCompleteResponseDto;
import com.georgeifrim.entities.responses.TrainingResponse;
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
}
