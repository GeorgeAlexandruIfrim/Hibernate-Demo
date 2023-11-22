package com.georgeifrim.HibernateDemo.repositories;

import com.georgeifrim.HibernateDemo.entities.Trainer;
import com.georgeifrim.HibernateDemo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Optional;

public interface TrainerRepo extends JpaRepository<Trainer, Integer> {

    Optional<Trainer> findByUserId(int userId);

    @Query("select t from Trainer t where t.user.username = ?1")
    Optional<Trainer> findByUserName(String username);

    List<Trainer> findAllByUserIsActive(boolean b);
}
