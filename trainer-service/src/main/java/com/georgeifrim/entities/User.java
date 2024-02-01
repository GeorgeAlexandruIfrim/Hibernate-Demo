package com.georgeifrim.entities;


import lombok.Data;
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
