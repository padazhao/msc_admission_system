package com.group13.msc_admission_system.exception;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * This is class is a custom error message class.
 * It is used to customize the error message sent as a response by the global exception handler.
 */
public class ExceptionErrorMessage {
    String message;
    String path;
    LocalDateTime timeStamp;
    Map<String, String> details;

    public ExceptionErrorMessage(String message, String path){  //USED WHEN THERE IS NO DETAILS
        this.message=message;
        this.path=path;
        this.timeStamp = LocalDateTime.now();
    }
    public ExceptionErrorMessage(String message, String path, Map<String, String> details ){  //USED WHEN DETAILS ARE SENT
        this.message=message;
        this.path=path;
        this.details=details;
        this.timeStamp = LocalDateTime.now();
    }
}
