package com.georgeifrim.HibernateDemo.entities.dto.requests;

public interface RequestDto {

    String getFirstName();
    String getLastName();

    default String getUsername(){
        return getFirstName() + "." + getLastName();
    }
}
