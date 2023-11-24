package com.georgeifrim.HibernateDemo.mappers;

import com.georgeifrim.HibernateDemo.entities.Trainee;
import com.georgeifrim.HibernateDemo.entities.User;
import com.georgeifrim.HibernateDemo.entities.dto.TraineeDto;
import com.georgeifrim.HibernateDemo.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TraineeMapper implements Mapper<Trainee, TraineeDto> {

    @Autowired
    private final UserRepo userRepo;

    @Override
    public Trainee toDomain(TraineeDto traineeDto){
        Trainee trainee = new Trainee();
        trainee.setDate_of_birth(traineeDto.getDate_of_birth());
        trainee.setAddress(traineeDto.getAddress());
        trainee.setUser(getUserbyId(traineeDto.getUserId()));
        return trainee;
    }

    @Override
    public TraineeDto toDto(Trainee trainee) {
        return null;
    }

    private User getUserbyId(int id){
        return userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
