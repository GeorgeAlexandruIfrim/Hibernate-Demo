package com.georgeifrim.HibernateDemo.repositories;

import com.georgeifrim.HibernateDemo.entities.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TraineeRepo extends JpaRepository<Trainee, Integer> {

        @Query("SELECT t FROM Trainee t WHERE t.user.username = ?1")
        Optional<Trainee> findTraineeByUserName(String username);

        @Query("SELECT t FROM Trainee t WHERE t.id = ?1")
        Optional<Trainee> findTraineeById(Integer id);

}
