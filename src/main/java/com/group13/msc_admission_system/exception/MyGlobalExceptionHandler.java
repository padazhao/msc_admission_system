package com.group13.msc_admission_system.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * This class is in charge of handling all the exceptions to the controller class.
 * It is a Controller class but for exceptions.
 * It receives all exceptions which should have been directed the controller and handles it.
 * It does this by sending each type of error to the method designed to handle that error.
 * RestControllerAdvice tells springboot to use this class for global exception handling
 * ExceptionHandler is used to map the types of error
 */
@RestControllerAdvice
public class MyGlobalExceptionHandler {

    @ExceptionHandler(MyResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<MyExceptionErrorMessage> notFoundException(MyResourceNotFoundException exception, HttpServletRequest request){
        return new ResponseEntity(new MyExceptionErrorMessage(exception.getMessage(), request.getServletPath()),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MyOutOfBoundException.class)
    public ResponseEntity<MyExceptionErrorMessage> notFoundException(MyOutOfBoundException exception, HttpServletRequest request){
        return new ResponseEntity(new MyExceptionErrorMessage(exception.getMessage(), request.getServletPath()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MyResourceAlreadyExistException.class)
    public ResponseEntity<MyExceptionErrorMessage> notFoundException(MyResourceAlreadyExistException exception, HttpServletRequest request){
        return new ResponseEntity(new MyExceptionErrorMessage(exception.getMessage(), request.getServletPath()),HttpStatus.CONFLICT);
    }

}
