package com.georgeifrim.HibernateDemo.services;

import com.georgeifrim.HibernateDemo.repositories.UserSecurityRepo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final UserSecurityRepo userSecurityRepo;

    public JpaUserDetailsService(UserSecurityRepo userSecurityRepo){
        this.userSecurityRepo = userSecurityRepo;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userSecurityRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("No such user is registered!"));
    }
}
