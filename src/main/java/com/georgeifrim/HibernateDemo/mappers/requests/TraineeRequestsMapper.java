package com.georgeifrim.HibernateDemo.mappers.requests;

import com.georgeifrim.HibernateDemo.entities.Trainee;
import com.georgeifrim.HibernateDemo.entities.User;
import com.georgeifrim.HibernateDemo.entities.dto.requests.TraineeRequestDto;
import com.georgeifrim.HibernateDemo.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TraineeRequestsMapper implements RequestsMapper<Trainee, TraineeRequestDto> {

    @Autowired
    private final UserRepo userRepo;

    @Override
    public Trainee toEntity(TraineeRequestDto traineeRequestDto){

        var userToBeCreated = new User(traineeRequestDto.getFirstName(), traineeRequestDto.getLastName(), false);

        return Trainee.builder()
                .address(traineeRequestDto.getAddress())
                .date_of_birth(traineeRequestDto.getDateOfBirth())
                .user(userRepo.save(userToBeCreated))
                .build();
    }
}
