package com.georgeifrim.HibernateDemo.repositories;

import com.georgeifrim.HibernateDemo.entities.Training;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingRepo extends JpaRepository<Training, Integer> {

    void deleteByTrainerId(Integer trainer_id);

    void deleteTrainingByName(String name);
}
