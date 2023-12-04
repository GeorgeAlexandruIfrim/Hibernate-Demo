package com.georgeifrim.HibernateDemo.entities.dto.requests;

import lombok.Data;

@Data
public class TrainerRequestDto {

    private int training_type_id;
    private int userId;
}
