package com.georgeifrim.HibernateDemo.entities.dto.responses;

import lombok.Data;

@Data
public class TraineeResponseDto {

    private String username;
    private String password;

    public TraineeResponseDto(String username, String password){
        this.username = username;
        this.password = password;
    }
}
