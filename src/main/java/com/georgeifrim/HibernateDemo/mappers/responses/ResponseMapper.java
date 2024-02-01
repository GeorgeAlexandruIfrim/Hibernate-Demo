package com.georgeifrim.HibernateDemo.mappers.responses;

public interface ResponseMapper<Entity, EntityResponseDto> {
    EntityResponseDto toResponseDto(Entity entity);
}

