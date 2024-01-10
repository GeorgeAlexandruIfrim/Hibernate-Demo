package com.georgeifrim.entities.requests;

import lombok.Data;
import com.georgeifrim.entities.HttpMethods;
import org.springframework.http.HttpMethod;

import java.time.LocalDate;

@Data
public class Training{

    private LocalDate date;
    private int duration_minutes;
    private String name;
    private int trainee_id;
    private int trainer_id;
    private int training_type_id;
    private HttpMethods httpMethod;

    public HttpMethod getHttpMethod(){
        return httpMethod.getHttpMethod();
    }

}