package com.georgeifrim.HibernateDemo.services;

import com.georgeifrim.HibernateDemo.mappers.requests.RequestsMapper;
import com.georgeifrim.HibernateDemo.mappers.responses.ResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class EntityService <Entity, EntityRequestDto, EntityResponseDto>{


    @Autowired
    public RequestsMapper<Entity, EntityRequestDto> requestsMapper;


    @Autowired
    public ResponseMapper<Entity, EntityResponseDto> responseMapper;

    public abstract EntityResponseDto create(EntityRequestDto entityRequestDto);
    public abstract Entity getById(Integer id);
    public abstract Entity getByUserName(String username);
    public abstract Entity update(Integer id, EntityRequestDto entityRequestDto);
    public abstract Entity updateActive(Integer id, boolean status);
    public abstract void delete(String username);



}
