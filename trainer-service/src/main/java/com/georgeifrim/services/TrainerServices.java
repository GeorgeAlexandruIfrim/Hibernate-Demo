package com.georgeifrim.services;

import com.georgeifrim.entities.TrainerCompleteResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

@Service
@AllArgsConstructor
public class TrainerServices {

    private final RestTemplate restTemplate;
    private static final String TRAINER_SERVICE_URL = "http://localhost:8080";
    private static final String SERVICE = "/trainers";
    private static final String SLASH = "/";
    private static final String AUTH_HEADER_NAME = "Authorization";

    private static final String AUTH_TYPE = "Basic ";

    private static final String SPLITTER = ":";

    public ResponseEntity<TrainerCompleteResponseDto> addTraining(String username) {
        String uri = TRAINER_SERVICE_URL + SERVICE + SLASH + username;

        String SecurityUsername = "Kimi.Nigel";
        String password = "Kimi.Nigel";
        String credentials = SecurityUsername + SPLITTER + password;
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());

        HttpHeaders headers = new HttpHeaders();
        headers.add(AUTH_HEADER_NAME, AUTH_TYPE + encodedCredentials);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<TrainerCompleteResponseDto> response = restTemplate.exchange(uri,
                HttpMethod.GET,
                entity,
                TrainerCompleteResponseDto.class);

        return response;
    }

}
