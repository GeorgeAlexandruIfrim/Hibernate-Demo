package com.georgeifrim.HibernateDemo.exceptions;

import com.georgeifrim.HibernateDemo.exceptions.login.PasswordNotMatching;
import com.georgeifrim.HibernateDemo.exceptions.trainees.TraineeWithUsernameNotFound;
import com.georgeifrim.HibernateDemo.exceptions.trainer.TrainerWithUsernameNotFound;
import com.georgeifrim.HibernateDemo.exceptions.trainingType.TrainingTypeDoesNotExist;
import com.georgeifrim.HibernateDemo.exceptions.users.UserWithUsernameAlreadyExists;
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

    @ExceptionHandler({UserWithUsernameAlreadyExists.class})
    public ResponseEntity<ErrorMessage> handleUserWithUsernameAlreadyExists(Exception e){
        var err = new ErrorMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
    }

    @ExceptionHandler({TrainingTypeDoesNotExist.class})
    public ResponseEntity<ErrorMessage> handleInvalidTrainingType(Exception e){
        var err = new ErrorMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler({PasswordNotMatching.class})
    public ResponseEntity<ErrorMessage> handleInvalidPasswordForUser(Exception e){
        var err = new ErrorMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);
    }
    @ExceptionHandler({TrainerWithUsernameNotFound.class})
    public ResponseEntity<ErrorMessage> handleTrainerWithUsernameNotExists(Exception e){
        var err = new ErrorMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }
}
