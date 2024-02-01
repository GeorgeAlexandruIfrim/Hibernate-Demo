package com.georgeifrim.HibernateDemo.repositories;

import com.georgeifrim.HibernateDemo.entities.TrainingType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingTypeRepo extends JpaRepository<TrainingType, Integer>{

    boolean existsByName(String name);

    TrainingType findByName(String name);
}

