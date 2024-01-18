package com.georgeifrim.mongoEntities;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
public class User {
    private String firstName;
    private String lastName;
}
