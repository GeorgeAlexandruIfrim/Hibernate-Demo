package com.georgeifrim.HibernateDemo.mappers.responses;

import com.georgeifrim.HibernateDemo.entities.Trainer;
import com.georgeifrim.HibernateDemo.entities.dto.responses.TrainerResponseDto;
import org.springframework.stereotype.Service;

@Service
public class TrainerResponseMapper implements ResponseMapper<Trainer, TrainerResponseDto>{


    @Override
    public Trainer toEntity(TrainerResponseDto trainerResponseDto) {
        return null;
    }

    @Override
    public TrainerResponseDto toResponseDto(Trainer trainer) {
        String username = trainer.getUser().getUsername();
        String password = trainer.getUser().getPassword();
        return new TrainerResponseDto(username, password);
    }
}
