package com.georgeifrim.HibernateDemo.entities.security;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Entity
@Table(name = "authorities")
@Data
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToMany(mappedBy = "authorities")
    private Set<UserSecurity> userSecurities;

    @Override
    public String getAuthority() {
        return name;
    }
}
