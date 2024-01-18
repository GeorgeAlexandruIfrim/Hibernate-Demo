package com.georgeifrim.mongoEntities;

import com.georgeifrim.entities.TraineeResponseDto;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class Trainer {

    @Id
    private String id;
    private User user;
    private List<Trainings> trainings;
}
