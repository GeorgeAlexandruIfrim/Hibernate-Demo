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

        var userToBeCreated = new User(traineeRequestDto.first_name(), traineeRequestDto.last_name(), false);

        return Trainee.builder()
                .address(traineeRequestDto.address())
                .date_of_birth(traineeRequestDto.date_of_birth())
                .user(userRepo.save(userToBeCreated))
                .build();
    }

    @Override
    public TraineeRequestDto toRequestDto(Trainee trainee) {
        return null;
    }

    private User getUserbyId(int id){
        return userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
