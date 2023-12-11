package com.georgeifrim.HibernateDemo.repositories;

import com.georgeifrim.HibernateDemo.entities.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TrainerRepo extends JpaRepository<Trainer, Integer> {

    Optional<Trainer> findByUserUsername(String username);

    List<Trainer> findAllByUserIsActive(boolean b);
}
