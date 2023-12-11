package com.georgeifrim.HibernateDemo.mappers.responses;

import com.georgeifrim.HibernateDemo.entities.Trainee;
import com.georgeifrim.HibernateDemo.entities.dto.responses.TraineeResponseDto;
import org.springframework.stereotype.Service;

@Service
public class TraineeResponseMapper implements ResponseMapper<Trainee, TraineeResponseDto> {

    @Override
    public TraineeResponseDto toResponseDto(Trainee trainee) {
        String username = trainee.getUser().getUsername();
        String password = trainee.getUser().getPassword();
        return new TraineeResponseDto(username, password);
    }
}
