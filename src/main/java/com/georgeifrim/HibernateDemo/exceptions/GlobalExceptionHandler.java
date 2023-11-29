package com.georgeifrim.HibernateDemo.exceptions;

import com.georgeifrim.HibernateDemo.exceptions.trainees.TraineeWithUsernameNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({TraineeWithUsernameNotFound.class})
    public ResponseEntity<ErrorMessage> handleUsernameNotFound(Exception e){
        ErrorMessage err = new ErrorMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }
}
