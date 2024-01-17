package com.georgeifrim.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;

@Getter
public enum HttpMethods {

    PUT(HttpMethod.PUT),
    DELETE (HttpMethod.DELETE);

    private final HttpMethod httpMethod;
    HttpMethods(HttpMethod httpMethod){
        this.httpMethod = httpMethod;
    }
}
