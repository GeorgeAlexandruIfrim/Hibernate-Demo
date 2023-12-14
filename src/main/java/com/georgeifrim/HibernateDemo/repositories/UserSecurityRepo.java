package com.georgeifrim.HibernateDemo.repositories;

import com.georgeifrim.HibernateDemo.entities.security.UserSecurity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserSecurityRepo extends JpaRepository<UserSecurity, Integer> {

    Optional<UserSecurity> findByUsername(String username);
}
