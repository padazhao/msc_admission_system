package com.group13.msc_admission_system.exception;

import com.group13.msc_admission_system.model.Applicant;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.management.JMException;
import java.util.HashMap;
import java.util.Map;

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
    public ModelAndView /*ResponseEntity<MyExceptionErrorMessage> */notFoundException(MyResourceNotFoundException exception, HttpServletRequest request){

        System.out.println(exception.getMessage());
        if(exception.getMessage().contains("APPLICANT")){
            ModelAndView modelAndView=  new ModelAndView("login_form");
            return modelAndView.addObject("applicant",new Applicant());
        }
        else  return new ModelAndView("ErrorPage");
        //return new ResponseEntity(new MyExceptionErrorMessage(exception.getNotification(), request.getServletPath()),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MyOutOfBoundException.class)
    public ModelAndView /*ResponseEntity<MyExceptionErrorMessage>*/ notFoundException(MyOutOfBoundException exception, HttpServletRequest request){
        //return new ResponseEntity(new MyExceptionErrorMessage(exception.getMessage(), request.getServletPath()),HttpStatus.BAD_REQUEST);
        return new ModelAndView("ErrorPage");
    }

    @ExceptionHandler(MyResourceAlreadyExistException.class)
    public ModelAndView /*ResponseEntity<MyExceptionErrorMessage>*/ notFoundException(MyResourceAlreadyExistException exception, HttpServletRequest request){
        //return new ResponseEntity(new MyExceptionErrorMessage(exception.getMessage(), request.getServletPath()),HttpStatus.CONFLICT);
        return new ModelAndView("ErrorPage");
    }

    @ExceptionHandler(MyInvalidInputException.class)
    public ModelAndView/*ResponseEntity<MyExceptionErrorMessage>*/ invalidInputException(MyInvalidInputException exception, HttpServletRequest request){
        //return new ResponseEntity(new MyExceptionErrorMessage(exception.getMessage(), request.getServletPath()),HttpStatus.BAD_REQUEST);
        return new ModelAndView("ErrorPage");
    }

    @ExceptionHandler(JMException.class)
    public ModelAndView/*ResponseEntity<MyExceptionErrorMessage>*/ jmsException(JMException exception, HttpServletRequest request){
        //return new ResponseEntity(new MyExceptionErrorMessage(exception.getMessage(), request.getServletPath()),HttpStatus.INTERNAL_SERVER_ERROR);
        return new ModelAndView("ErrorPage");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ModelAndView/*ResponseEntity<MyExceptionErrorMessage> */ invalidInputException(MethodArgumentNotValidException exception, HttpServletRequest request){

        //RECIPIENT TO STORE THE K,V PAIR OF ERROR AND ERROR MESSAGE FROM EXCEPTION
        Map<String, String> bindingDetails = new HashMap<>();

        //EXTRACTION OF K,V FROM EXCEPTION AND POPULATING RECIPIENT
        exception.getBindingResult().getFieldErrors().forEach(
                fieldError -> bindingDetails.put(fieldError.getField(),
                        fieldError.getDefaultMessage()));

        //CREATING,POPULATING AND RETURNING AN EXCEPTION ERROR MESSAGE OBJ VIA A BAD REQUEST RESPONSE ENTITY
//        return new ResponseEntity(
//                new MyExceptionErrorMessage(MyMessage.invalidInput,request.getServletPath(), bindingDetails),
//                HttpStatus.BAD_REQUEST);
        return new ModelAndView("ErrorPage");
    }

}
