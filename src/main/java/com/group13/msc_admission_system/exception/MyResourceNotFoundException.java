package com.group13.msc_admission_system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

/**
 * This is a custom created class to return an exception when a resource is not found.
 * It accepts a message as parameter to explain the error.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class MyResourceNotFoundException extends RuntimeException{

    public MyResourceNotFoundException(String message) {
        super(message);
    }

}


//
//    @Serial
//    private static final long serialVersionUID=1L;
//    private String ResourceName;
//    private String FieldName;
//    private Object FieldValue;
//
//
//        public MyResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
//        super(String.format("%s not found with %s : '%s'", resourceName, fieldName,fieldValue));
//        ResourceName = resourceName;
//        FieldName = fieldName;
//        FieldValue = fieldValue;
//    }
//
//    public String getResourceName() {
//        return ResourceName;
//    }
//
//    public String getFieldName() {
//        return FieldName;
//    }
//
//    public Object getFieldValue() {
//        return FieldValue;
//    }

