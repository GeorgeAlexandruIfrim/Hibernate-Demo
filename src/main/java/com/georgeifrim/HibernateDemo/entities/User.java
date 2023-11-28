package com.georgeifrim.HibernateDemo.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.Random;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String first_name;
    private String last_name;
    private String username;
    private String password;
    private boolean isActive;

    @CreatedDate
    @Temporal(TemporalType.DATE)
    private LocalDate created;
    
    public User(String first_name, String last_name, boolean isActive) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = first_name + "." + last_name;
        this.password = passwordGenerator();
        this.isActive = isActive;
    }

    private String passwordGenerator() {
            final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
            int passwordLength = 10;
            Random random = new Random();
            StringBuilder stringBuilder = new StringBuilder(passwordLength);

            for (int i = 0; i < passwordLength; i++) {
                int randomIndex = random.nextInt(CHARACTERS.length());
                char randomChar = CHARACTERS.charAt(randomIndex);
                stringBuilder.append(randomChar);
            }
            return stringBuilder.toString();
        }
}
