package com.georgeifrim.HibernateDemo.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Trainee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.DATE)
    private LocalDate date_of_birth;

    private String address;

    @OneToMany(mappedBy = "trainee", cascade = CascadeType.PERSIST)
    private List<Training> trainings;

    @OneToOne
    private User user;

}
