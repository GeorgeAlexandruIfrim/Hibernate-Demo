package com.georgeifrim.HibernateDemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.georgeifrim.HibernateDemo.entities.Trainee;
import com.georgeifrim.HibernateDemo.entities.User;
import com.georgeifrim.HibernateDemo.entities.dto.requests.TraineeRequestDto;
import com.georgeifrim.HibernateDemo.entities.dto.responses.TraineeCompleteResponseDto;
import com.georgeifrim.HibernateDemo.entities.dto.responses.TraineeResponseDto;
import com.georgeifrim.HibernateDemo.mappers.requests.TraineeRequestsMapper;
import com.georgeifrim.HibernateDemo.mappers.responses.TraineeCompleteResponseMapper;
import com.georgeifrim.HibernateDemo.mappers.responses.TraineeResponseMapper;
import com.georgeifrim.HibernateDemo.repositories.TraineeRepo;
import com.georgeifrim.HibernateDemo.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Base64;
import java.util.Collections;
import java.util.Optional;
import java.util.Random;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TraineeControllerIntegrationTest {
    private static final String AUTH_HEADER_NAME = "Authorization";
    private static final String AUTH_TYPE = "Basic ";
    private static final String SEPARATOR = ":";
    private static final String SecurityUsername = "Andrei.Lupulescu";
    private static final String password = "Andrei.Lupulescu";
    private static final String credentials = SecurityUsername + SEPARATOR + password;
    private static final String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());
    private final static String USERNAME = "firstName.lastName";
    private final static int RANDOM_ID = new Random().nextInt();

    @MockBean
    private Trainee trainee;

    @MockBean
    private TraineeRepo traineeRepo;

    @MockBean
    private TraineeRequestsMapper requestsMapper;

    @MockBean
    private TraineeResponseDto traineeResponseDto;

    @MockBean
    private TraineeResponseMapper responseMapper;

    @MockBean
    private UserService userService;

    @MockBean
    private TraineeCompleteResponseMapper traineeCompleteResponseMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    private final TraineeRequestDto traineeRequestDto = new TraineeRequestDto();
    private TraineeCompleteResponseDto traineeCompleteResponseDto;
    private final HttpHeaders headers = new HttpHeaders();
    private final User user = new User();
    @BeforeEach
    public void setUp(){
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add(AUTH_HEADER_NAME, AUTH_TYPE + encodedCredentials);

        traineeRequestDto.setFirstName("firstName");
        traineeRequestDto.setLastName("lastName");

        this.traineeCompleteResponseDto = new TraineeCompleteResponseDto(null,null,null,null, true, null);
    }
    @Test
    public void traineeByUsername() throws Exception {

        when(traineeRepo.findByUserUsername(anyString())).thenReturn(Optional.of(trainee));

        mockMvc.perform(get("/trainees/" + USERNAME)
                        .headers(headers))
                .andExpect(status().isOk());
    }

    @Test
    public void traineeByUsernameWithNonExistingUser() throws Exception {

        when(traineeRepo.findByUserUsername(anyString())).thenReturn(Optional.empty());

        mockMvc.perform(get("/trainees/" + USERNAME)
                        .headers(headers))
                .andExpect(status().isNotFound());
    }

    @Test
    public void listOfTrainingsByTraineeId() throws Exception {
        when(traineeRepo.findById(anyInt())).thenReturn(Optional.of(trainee));

        mockMvc.perform(get("/trainees/" + RANDOM_ID + "/trainings")
                        .headers(headers))
                .andExpect(status().isOk());
    }

    @Test
    public void listOfTrainingsByNonExistingTraineeById() throws Exception {
        when(traineeRepo.findById(anyInt())).thenReturn(Optional.empty());

        mockMvc.perform(get("/trainees/" + RANDOM_ID + "/trainings")
                        .headers(headers))
                .andExpect(status().isNotFound());
    }

    @Test
    public void addNewTrainee() throws Exception{

        when(userService.userWithUsernameExists(USERNAME)).thenReturn(false);
        when(requestsMapper.toEntity(traineeRequestDto)).thenReturn(trainee);
        when(traineeRepo.save(trainee)).thenReturn(trainee);
        when(responseMapper.toResponseDto(trainee)).thenReturn(traineeResponseDto);

        mockMvc.perform(put("/trainees")
                        .headers(headers)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(traineeRequestDto)))
                .andExpect(status().isCreated());
    }

    @Test
    public void addTraineeWhenAlreadyExistsOneWithThisUsername() throws Exception{

        when(userService.userWithUsernameExists(USERNAME)).thenReturn(true);
        when(requestsMapper.toEntity(traineeRequestDto)).thenReturn(trainee);
        when(traineeRepo.save(trainee)).thenReturn(trainee);
        when(responseMapper.toResponseDto(trainee)).thenReturn(traineeResponseDto);

        mockMvc.perform(put("/trainees")
                        .headers(headers)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(traineeRequestDto)))
                .andExpect(status().isConflict());
    }

    @Test
    public void updateTraineeAddressAndDateOfBirthIfExists() throws Exception{

        when(traineeRepo.findByUserUsername(USERNAME)).thenReturn(Optional.of(trainee));
        when(traineeRepo.save(trainee)).thenReturn(trainee);
        when(traineeCompleteResponseMapper.toResponseDto(trainee)).thenReturn(traineeCompleteResponseDto);

        mockMvc.perform(post("/trainees")
                        .headers(headers)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(traineeRequestDto)))
                .andExpect(status().isOk());
    }

    @Test
    public void updateNonExistingTrainee() throws Exception{

        when(traineeRepo.findByUserUsername(USERNAME)).thenReturn(Optional.empty());
        when(traineeRepo.save(trainee)).thenReturn(trainee);
        when(traineeCompleteResponseMapper.toResponseDto(trainee)).thenReturn(traineeCompleteResponseDto);

        mockMvc.perform(post("/trainees")
                        .headers(headers)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(traineeRequestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateIsActiveforExistingTrainee() throws Exception{

        when(traineeRepo.findByUserUsername(USERNAME)).thenReturn(Optional.of(trainee));
        when(traineeRepo.save(trainee)).thenReturn(trainee);
        when(trainee.getUser()).thenReturn(user);

        mockMvc.perform(post("/trainees/updateStatus")
                        .headers(headers)
                        .param("status", Boolean.toString(true))
                        .param("username", USERNAME))
                .andExpect(status().isOk());
    }

}




