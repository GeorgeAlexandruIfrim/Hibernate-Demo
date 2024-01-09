package com.georgeifrim.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;
import java.time.LocalDate;

@Data
public class User {

    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private boolean isActive;
    private Instant blockedUntil;

    private LocalDate created;
}
