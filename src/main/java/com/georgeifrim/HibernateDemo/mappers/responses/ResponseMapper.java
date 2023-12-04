package com.georgeifrim.HibernateDemo.mappers.responses;

public interface ResponseMapper<Entity, EntityResponseDto> {

    Entity toEntity(EntityResponseDto entityResponseDto);
    EntityResponseDto toResponseDto(Entity entity);
}

