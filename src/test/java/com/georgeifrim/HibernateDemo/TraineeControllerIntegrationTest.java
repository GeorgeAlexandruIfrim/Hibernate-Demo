package com.georgeifrim.HibernateDemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.georgeifrim.HibernateDemo.entities.User;
import com.georgeifrim.HibernateDemo.entities.dto.requests.TraineeRequestDto;
import com.georgeifrim.HibernateDemo.entities.dto.responses.TraineeCompleteResponseDto;
import com.georgeifrim.HibernateDemo.entities.dto.responses.TraineeResponseDto;
import com.georgeifrim.HibernateDemo.mappers.responses.TraineeCompleteResponseMapper;
import com.georgeifrim.HibernateDemo.repositories.TraineeRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.util.Base64;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TraineeControllerIntegrationTest {
    private static final String AUTH_HEADER_NAME = "Authorization";
    private static final String AUTH_TYPE = "Basic ";
    private static final String SEPARATOR = ":";
    private static final String SecurityUsername = "Kimi.Nigel";
    private static final String password = "Kimi.Nigel";
    private static final String credentials = SecurityUsername + SEPARATOR + password;
    private static final String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());
    private final static String USERNAME = "Andrei.Lupulescu";
    private final static int RANDOM_ID = 18;


    @Autowired
    private TraineeRepo traineeRepo;

    @Autowired
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
    }
    @Test
    public void traineeByUsername() throws Exception {

        mockMvc.perform(get("/trainees/" + USERNAME)
                        .headers(headers))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lastName").value("Lupulescu"))
                .andExpect(jsonPath("$.firstName").value("Andrei"))
                .andExpect(jsonPath("$.dateOfBirth").value("1949-08-22"));
    }

    @Test
    public void traineeByUsernameWithNonExistingUser() throws Exception {

        mockMvc.perform(get("/trainees/" + USERNAME +"nonExistingUser")
                        .headers(headers))
                .andExpect(status().isNotFound());
    }

    @Test
    public void listOfTrainingsByTraineeId() throws Exception {

        mockMvc.perform(get("/trainees/" + RANDOM_ID + "/trainings")
                        .headers(headers))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].name").value("December Killer"))
                .andExpect(jsonPath("$.[0].date").value("2023-12-23"))
                .andExpect(jsonPath("$.[0].durationMinutes").value(35));
    }

    @Test
    public void listOfTrainingsByNonExistingTraineeById() throws Exception {

        mockMvc.perform(get("/trainees/" + Integer.MAX_VALUE + "/trainings")
                        .headers(headers))
                .andExpect(status().isNotFound());
    }

    @Test
    public void addNewTrainee() throws Exception{

        traineeRequestDto.setFirstName("Ciprian");
        traineeRequestDto.setLastName("Porumbescu");
        traineeRequestDto.setDateOfBirth(LocalDate.of(1853, 10,14));
        traineeRequestDto.setAddress("Sipotele Sucevei");



       MvcResult result = mockMvc.perform(put("/trainees")
                                    .headers(headers)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(traineeRequestDto)))
                            .andExpect(status().isCreated())
                            .andReturn();

       String resultAsString = result.getResponse().getContentAsString();
       TraineeResponseDto actualResponse = objectMapper.readValue(resultAsString, TraineeResponseDto.class);

       assertEquals("Ciprian.Porumbescu", actualResponse.getUsername());

    }

    @Test
    public void addTraineeWhenAlreadyExistsOneWithThisUsername() throws Exception{

        traineeRequestDto.setFirstName("Ciprian");
        traineeRequestDto.setLastName("Porumbescu");
        traineeRequestDto.setDateOfBirth(LocalDate.of(1853, 10,14));
        traineeRequestDto.setAddress("Sipotele Sucevei");

        mockMvc.perform(put("/trainees")
                        .headers(headers)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(traineeRequestDto)))
                .andExpect(status().isConflict());
    }

    @Test
    public void updateTraineeAddressAndDateOfBirthIfExists() throws Exception{

        traineeRequestDto.setFirstName("Ciprian");
        traineeRequestDto.setLastName("Porumbescu");
        traineeRequestDto.setDateOfBirth(LocalDate.of(1853, 10,24));
        traineeRequestDto.setAddress("Sipotele Iasului");

        mockMvc.perform(post("/trainees")
                        .headers(headers)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(traineeRequestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.address").value("Sipotele Iasului"))
                .andExpect(jsonPath("$.dateOfBirth").value("1853-10-24"));
    }

    @Test
    public void updateNonExistingTrainee() throws Exception{

        traineeRequestDto.setFirstName("Non");
        traineeRequestDto.setLastName("Existing");

        mockMvc.perform(post("/trainees")
                        .headers(headers)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(traineeRequestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateIsActiveforExistingTrainee() throws Exception{


        mockMvc.perform(post("/trainees/updateStatus")
                        .headers(headers)
                        .param("status", Boolean.toString(true))
                        .param("username", USERNAME))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user.active").value("true"));
    }

    @Test
    public void updateIsActiveForANonExistingUser() throws Exception{

        String username = "nonExistingUsername";

        mockMvc.perform(post("/trainees/updateStatus")
                        .headers(headers)
                        .param("status", Boolean.toString(true))
                        .param("username", username))
                .andExpect(status().isNotFound());

    }

}




