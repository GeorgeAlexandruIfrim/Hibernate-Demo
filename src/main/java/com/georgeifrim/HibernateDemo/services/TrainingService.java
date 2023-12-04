package com.georgeifrim.HibernateDemo.services;

import com.georgeifrim.HibernateDemo.entities.Training;
import com.georgeifrim.HibernateDemo.entities.dto.requests.TrainingRequestDto;
import com.georgeifrim.HibernateDemo.exceptions.training.TrainingWithIdNotFound;
import com.georgeifrim.HibernateDemo.mappers.requests.RequestsMapper;
import com.georgeifrim.HibernateDemo.repositories.TrainingRepo;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Log4j2
public class TrainingService {

    private final TrainingRepo trainingRepo;

    private final RequestsMapper<Training, TrainingRequestDto> trainingRequestsMapper;


    public Training createTraining(TrainingRequestDto trainingRequestDto) {

        Training training = trainingRequestsMapper.toEntity(trainingRequestDto);

        log.info("Training created");
        return trainingRepo.save(training);
    }

    public Training getTrainingById(Integer id) {
        return trainingRepo.findById(id)
                            .orElseThrow(() -> new TrainingWithIdNotFound(id));
    }
}
