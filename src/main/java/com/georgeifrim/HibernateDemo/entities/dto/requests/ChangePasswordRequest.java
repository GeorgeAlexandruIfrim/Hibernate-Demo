package com.georgeifrim.HibernateDemo.entities.dto.requests;

public record ChangePasswordRequest(
        String username,
        String oldPassword,
        String newPassword
) {
}
