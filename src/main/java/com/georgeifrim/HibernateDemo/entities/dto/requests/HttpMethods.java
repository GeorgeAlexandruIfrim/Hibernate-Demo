package com.georgeifrim.HibernateDemo.entities.dto.requests;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;

@RequiredArgsConstructor
@Getter
public enum HttpMethods {

    PUT(HttpMethod.PUT),
    DELETE(HttpMethod.DELETE);

    private HttpMethod httpMethod;

    HttpMethods (HttpMethod httpMethod){
        this.httpMethod = httpMethod;
    }

}
