package com.georgeifrim.HibernateDemo.repositories;

import com.georgeifrim.HibernateDemo.entities.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrainerRepo extends JpaRepository<Trainer, Integer> {

    Optional<Trainer> findByUserId(int userId);
}
