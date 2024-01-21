package com.georgeifrim.HibernateDemo;


import com.georgeifrim.HibernateDemo.entities.Trainee;
import com.georgeifrim.HibernateDemo.entities.Trainer;
import com.georgeifrim.HibernateDemo.entities.User;
import com.georgeifrim.HibernateDemo.entities.dto.requests.TraineeRequestDto;
import com.georgeifrim.HibernateDemo.entities.dto.responses.TraineeCompleteResponseDto;
import com.georgeifrim.HibernateDemo.entities.dto.responses.TraineeResponseDto;
import com.georgeifrim.HibernateDemo.exceptions.trainees.TraineeWithUsernameNotFound;
import com.georgeifrim.HibernateDemo.exceptions.users.UserWithUsernameAlreadyExists;
import com.georgeifrim.HibernateDemo.mappers.requests.TraineeRequestsMapper;
import com.georgeifrim.HibernateDemo.mappers.responses.TraineeCompleteResponseMapper;
import com.georgeifrim.HibernateDemo.mappers.responses.TraineeResponseMapper;
import com.georgeifrim.HibernateDemo.repositories.TraineeRepo;
import com.georgeifrim.HibernateDemo.repositories.TrainerRepo;
import com.georgeifrim.HibernateDemo.services.TraineeService;
import com.georgeifrim.HibernateDemo.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TraineeServiceTest {

    public static final String USERNAME = "username";
    @Mock
    private TraineeRepo traineeRepo;
    @Mock
    private TrainerRepo trainerRepo;
    @Mock
    private Trainee trainee;
    @Mock
    private Trainer trainer;
    @Mock
    private TraineeRequestsMapper traineeMapper;
    @Mock
    private TraineeResponseMapper responseMapper;
    @Mock
    private TraineeCompleteResponseMapper traineeCompleteResponseMapper;
    @Mock
    private TraineeCompleteResponseDto traineeCompleteResponseDto;
    @Mock
    private TraineeResponseDto traineeResponseDto;
    @Mock
    private UserService userService;
    @Mock
    private TraineeRequestDto traineeRequestDto;
    @InjectMocks
    private TraineeService traineeService;


    @Test
    public void createTrainee_fromTraineeDto_returnCreatedTrainee() {

        //When
        when(traineeRequestDto.getUsername()).thenReturn(USERNAME);
        when(userService.userWithUsernameExists(USERNAME)).thenReturn(false);
        when(traineeMapper.toEntity(traineeRequestDto)).thenReturn(trainee);
        when(traineeRepo.save(trainee)).thenReturn(trainee);
        when(responseMapper.toResponseDto(trainee)).thenReturn(traineeResponseDto);
        //Then
        TraineeResponseDto trainee = traineeService.create(traineeRequestDto);
        //Assert
        assertEquals(traineeResponseDto, trainee);
    }
    @Test
    public void createTraineeWithAUserThatAlreadyExists(){
        //When
        when(traineeRequestDto.getUsername()).thenReturn(USERNAME);
        when(userService.userWithUsernameExists(USERNAME)).thenReturn(true);
        when(traineeMapper.toEntity(traineeRequestDto)).thenReturn(trainee);
        when(traineeRepo.save(trainee)).thenReturn(trainee);
        when(responseMapper.toResponseDto(trainee)).thenReturn(traineeResponseDto);
        //Assert
        assertThrows(UserWithUsernameAlreadyExists.class, () -> traineeService.create(traineeRequestDto));
    }

    @Test
    public void getTraineeResponseDtoByUsername(){
        //When
        when(traineeRepo.findByUserUsername(USERNAME)).thenReturn(Optional.of(trainee));
        when(traineeCompleteResponseMapper.toResponseDto(trainee)).thenReturn(traineeCompleteResponseDto);
        //Then
        TraineeCompleteResponseDto trainee = traineeService.getByUserName(USERNAME);
        //Assert
        assertEquals(trainee, traineeCompleteResponseDto);
    }

    @Test
    public void getTraineeResponseDtoWhenNoTraineeWithUsernameExists(){
        //When
        when(traineeRepo.findByUserUsername(USERNAME)).thenReturn(Optional.empty());
        when(traineeCompleteResponseMapper.toResponseDto(trainee)).thenReturn(traineeCompleteResponseDto);
        //Assert
        assertThrows(TraineeWithUsernameNotFound.class, () -> traineeService.getByUserName(USERNAME));
    }

    @Test
    public void updateTraineeAddressAndDateOfBirth(){
        //When
        when(traineeRequestDto.getUsername()).thenReturn(USERNAME);
        when(traineeRepo.findByUserUsername(USERNAME)).thenReturn(Optional.of(trainee));
        when(traineeCompleteResponseMapper.toResponseDto(trainee)).thenReturn(traineeCompleteResponseDto);

        var trainee = traineeService.update(traineeRequestDto);

        //Assert
        assertEquals(traineeCompleteResponseDto, trainee);

    }
    @Test
    public void updateTraineeThatDoesntExist(){
        //When
        when(traineeRequestDto.getUsername()).thenReturn(USERNAME);
        when(traineeRepo.findByUserUsername(USERNAME)).thenReturn(Optional.empty());
        when(traineeCompleteResponseMapper.toResponseDto(trainee)).thenReturn(traineeCompleteResponseDto);
        //Assert
        assertThrows(TraineeWithUsernameNotFound.class,() -> traineeService.update(traineeRequestDto));
    }

    @Test
    public void updateActiveForExistingTrainee(){
        //Given
        User user = new User();
        user.setActive(false);
        //When
        when(traineeRepo.findByUserUsername(USERNAME)).thenReturn(Optional.of(trainee));
        when(trainee.getUser()).thenReturn(user);

        traineeService.updateActive(USERNAME, true);

        assertTrue(trainee.getUser().getActive());
    }

    @Test
    public void enrollTrainerToTrainee(){
        //Given
        when(traineeRepo.findById(1)).thenReturn(Optional.of(trainee));
        when(trainerRepo.findById(2)).thenReturn(Optional.of(trainer));
        when(trainee.getTrainers()).thenReturn(new ArrayList<>());

        //Then
        traineeService.enrollTrainer(1,2);

        List<Trainer> trainers = trainee.getTrainers();
        assertTrue(trainers.contains(trainer));
    }





}
