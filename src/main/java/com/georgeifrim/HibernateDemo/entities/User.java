package com.georgeifrim.HibernateDemo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Random;

@Entity
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
    private boolean is_active;
    
    public User(String first_name, String last_name, boolean is_active) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = first_name + "." + last_name;
        this.password = passwordGenerator();
        this.is_active = is_active;
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
