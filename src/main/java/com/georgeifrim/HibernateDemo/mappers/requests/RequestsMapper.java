package com.georgeifrim.HibernateDemo.mappers.requests;

public interface RequestsMapper<Entity, EntityRequestDto> {

    Entity toEntity(EntityRequestDto entityRequestDto);
    EntityRequestDto toRequestDto(Entity entity);
}
