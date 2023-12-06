package com.georgeifrim.HibernateDemo.entities.dto.requests;

public record LoginRequest (
        String username,
        String password
){
}
