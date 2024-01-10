package com.georgeifrim.services;

import com.georgeifrim.entities.TrainerCompleteResponseDto;
import com.georgeifrim.entities.requests.Training;
import com.georgeifrim.entities.requests.TrainingWithNoHttpMethod;
import com.georgeifrim.mappers.TrainerMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.Objects;

@Service
@AllArgsConstructor
public class TrainerServices {

    private final RestTemplate restTemplate;
    private static final String MAIN_SERVICE_URL = "http://localhost:8080";
    private static final String TRAINERS = "/trainers";
    private static final String SLASH = "/";
    private static final String AUTH_HEADER_NAME = "Authorization";
    private static final String AUTH_TYPE = "Basic ";
    private static final String SEPARATOR = ":";
    private static final String SecurityUsername = "Kimi.Nigel";
    private static final String password = "Kimi.Nigel";
    private static final String credentials = SecurityUsername + SEPARATOR + password;
    private static final String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());

    public ResponseEntity<TrainerCompleteResponseDto> addOrDeleteTrainingFromTrainer(String username, Training training) {
        String trainersUri = MAIN_SERVICE_URL + TRAINERS + SLASH + username;

        TrainingWithNoHttpMethod trainingNoHttp = new TrainingWithNoHttpMethod(training);

        HttpHeaders headers = new HttpHeaders();
        headers.add(AUTH_HEADER_NAME, AUTH_TYPE + encodedCredentials);

        HttpEntity<TrainingWithNoHttpMethod> entity = new HttpEntity<>(trainingNoHttp, headers);

        restTemplate.exchange(trainersUri, training.getHttpMethod(), entity, Training.class);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<TrainerMapper> trainer(String username) {
        String trainersUri = MAIN_SERVICE_URL + TRAINERS + SLASH + username;

        HttpHeaders headers = new HttpHeaders();
        headers.add(AUTH_HEADER_NAME, AUTH_TYPE + encodedCredentials);

        HttpEntity<Training> entity = new HttpEntity<>(headers);

        ResponseEntity<TrainerCompleteResponseDto> response = restTemplate.exchange(trainersUri,
                HttpMethod.GET,
                entity,
                TrainerCompleteResponseDto.class);
        var trainerMapper = new TrainerMapper().toTrainerMapper(Objects.requireNonNull(response.getBody()));
        return ResponseEntity.ok(trainerMapper);
    }
}
