package com.georgeifrim.HibernateDemo.services;

import com.georgeifrim.HibernateDemo.mappers.Mapper;

public abstract class EntityService <Entity, EntityDto>{

    public Mapper<Entity, EntityDto> mapper;

    public abstract Entity create(EntityDto entityDto);
    public abstract Entity getById(Integer id);
    public abstract Entity getByUserName(String username);
    public abstract Entity update(Integer id, EntityDto entityDto);
    public abstract Entity updateActive(Integer id, boolean status);
    public abstract void delete(String username);



}
