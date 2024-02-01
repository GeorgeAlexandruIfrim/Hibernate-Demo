package com.georgeifrim.HibernateDemo.repositories;

import com.georgeifrim.HibernateDemo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {

    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);

    List<User> findByIsActiveTrueAndBlockedUntilBefore(Instant now);
}

