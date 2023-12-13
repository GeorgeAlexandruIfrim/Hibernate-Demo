package com.georgeifrim.HibernateDemo.repositories;

import com.georgeifrim.HibernateDemo.entities.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TraineeRepo extends JpaRepository<Trainee, Integer> {

        Optional<Trainee> findByUserUsername(String username);
        boolean existsByUserUsername(String username);


}
