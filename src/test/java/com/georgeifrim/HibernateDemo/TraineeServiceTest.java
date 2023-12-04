package com.georgeifrim.HibernateDemo;


import com.georgeifrim.HibernateDemo.entities.Trainee;
import com.georgeifrim.HibernateDemo.entities.User;
import com.georgeifrim.HibernateDemo.entities.dto.requests.TraineeRequestDto;
import com.georgeifrim.HibernateDemo.exceptions.trainees.TraineeWithIdNotFound;
import com.georgeifrim.HibernateDemo.mappers.requests.TraineeRequestsMapper;
import com.georgeifrim.HibernateDemo.repositories.TraineeRepo;
import com.georgeifrim.HibernateDemo.services.TraineeService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TraineeServiceTest {

    @Mock
    private TraineeRepo traineeRepo;

    @Mock
    private TraineeRequestsMapper traineeMapper;

    @InjectMocks
    private TraineeService traineeService;

//    @Test
//    public void createTrainee_fromTraineeDto_returnCreatedTrainee() {
//
//        //Given
//        TraineeRequestDto traineeRequestDto = new TraineeRequestDto();
//        traineeRequestDto.setAddress("Tineretului 15");
//        traineeRequestDto.setDate_of_birth(LocalDate.of(1990, 04, 11));
//        traineeRequestDto.setUserId(3);
//
//        User user = new User();
//        user.setId(3);
//        user.setUsername("Andrei");
//
//        Trainee traineeToBeSaved = new Trainee();
//        traineeToBeSaved.setDate_of_birth(LocalDate.of(1990, 04, 11));
//        traineeToBeSaved.setAddress("Tineretului 15");
//        traineeToBeSaved.setUser(user);
//
//
//        //When
//        when(traineeMapper.toEntity(traineeRequestDto)).thenReturn(traineeToBeSaved);
//        when(traineeRepo.save(traineeToBeSaved)).thenReturn(traineeToBeSaved);
//
//        //Then
//        Trainee trainee = traineeService.create(traineeRequestDto);
//
//        //Assert
//        assertEquals(traineeToBeSaved, trainee);
//    }
//
//    @Test
//    public void updateActive_ExistingTraineeId_returnUpdatedTrainee(){
//
//        //Given
//        int existingTraineeId = 2;
//        boolean newStatus = true;
//        User user = new User();
//        user.setActive(false);
//        Trainee existingTrainee = new Trainee();
//        existingTrainee.setId(existingTraineeId);
//        existingTrainee.setUser(user);
//
//        //When
//        when(traineeService.traineeWithIdExists(existingTraineeId)).thenReturn(true);
//        when(traineeRepo.findById(existingTraineeId)).thenReturn(Optional.of(existingTrainee));
//        when(traineeRepo.save(existingTrainee)).thenReturn(existingTrainee);
//
//        //Then
//        Trainee updatedTrainee = traineeService.updateActive(existingTraineeId, newStatus);
//
//        assertTrue(updatedTrainee.getUser().isActive());
//    }
//
//    @Test
//    public void updateActive_NonExistingTrainee_throwsException(){
//
//        //Given
//        int nonExistingId = 3;
//        boolean newStatus = true;
//
//        //When
//        when(traineeService.traineeWithIdExists(nonExistingId)).thenReturn(false);
//
//        //Then
//        assertThrows(TraineeWithIdNotFound.class, () -> traineeService.updateActive(nonExistingId, newStatus));
//    }
//
//


}
