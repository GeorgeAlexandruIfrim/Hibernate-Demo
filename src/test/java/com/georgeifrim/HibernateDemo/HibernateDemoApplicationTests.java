package com.georgeifrim.HibernateDemo;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.georgeifrim.HibernateDemo.entities.*;
import com.georgeifrim.HibernateDemo.entities.dto.requests.TraineeRequestDto;
import com.georgeifrim.HibernateDemo.entities.dto.responses.TraineeResponseDto;
import com.georgeifrim.HibernateDemo.repositories.TraineeRepo;
import com.georgeifrim.HibernateDemo.repositories.UserRepo;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class HibernateDemoApplicationTests {

	@LocalServerPort
	private Integer port;
	static DockerImageName myImage = DockerImageName.parse("mariadb:latest").asCompatibleSubstituteFor("mysql");
	static MySQLContainer container = new MySQLContainer(myImage);
	@DynamicPropertySource
	static void configureProperties(DynamicPropertyRegistry registry){
		registry.add("spring.datasource.url", container::getJdbcUrl);
		registry.add("spring.datasource.username", container::getUsername);
		registry.add("spring.datasource.password", container::getPassword);
	}

	private User user = new User("Andrei","Lupulescu", true);
	private Trainee trainee = Trainee.builder().address("Acasa").date_of_birth(LocalDate.of(1949,8,22)).user(new User("Andrei", "Lupulescu", true)).build();

	@BeforeEach
	void setup(){
		RestAssured.baseURI = "http://localhost:" + port;
		userRepo.save(user);
		traineeRepo.save(trainee);
	}
	@BeforeAll
	static void start(){
		container.start();

	}
	@AfterAll
	static void stop(){
		container.stop();
	}

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private TraineeRepo traineeRepo;

	@Autowired
	private UserRepo userRepo;

	private TraineeRequestDto traineeRequestDto = new TraineeRequestDto();

	private ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

	@Test
	public void addNewTrainee() throws Exception{

		traineeRequestDto.setFirstName("Ciprian");
		traineeRequestDto.setLastName("Porumbescu");
		traineeRequestDto.setDateOfBirth(LocalDate.of(1853, 10,14));
		traineeRequestDto.setAddress("Sipotele Sucevei");

		MvcResult result = mockMvc.perform(put("/trainees")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(traineeRequestDto)))
				.andExpect(status().isCreated())
				.andReturn();

		String resultAsString = result.getResponse().getContentAsString();
		TraineeResponseDto actualResponse = objectMapper.readValue(resultAsString, TraineeResponseDto.class);

		assertEquals("Ciprian.Porumbescu", actualResponse.getUsername());

	}
	@Test
	public void addNewTraineeWhenItAlreadyExists() throws Exception {

		traineeRequestDto.setFirstName("Andrei");
		traineeRequestDto.setLastName("Lupulescu");
		traineeRequestDto.setDateOfBirth(LocalDate.of(1853, 10,14));
		traineeRequestDto.setAddress("Sipotele Sucevei");

		mockMvc.perform(put("/trainees")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(traineeRequestDto)))
				.andExpect(status().isConflict());

	}
}
