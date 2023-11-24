package com.georgeifrim.HibernateDemo.mappers;

public interface Mapper<Entity, EntityDto > {

    Entity toDomain(EntityDto entityDto);
    EntityDto toDto(Entity entity);
}
