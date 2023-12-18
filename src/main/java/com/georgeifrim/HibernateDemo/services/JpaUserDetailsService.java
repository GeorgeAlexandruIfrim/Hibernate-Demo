package com.georgeifrim.HibernateDemo.services;

import com.georgeifrim.HibernateDemo.entities.security.UserSecurity;
import com.georgeifrim.HibernateDemo.repositories.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    public JpaUserDetailsService(UserRepo userRepo){
        this.userRepo = userRepo;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username)
                .map(UserSecurity::new)
                .orElseThrow(() -> new RuntimeException("No such user is registered!"));
    }
}
