package com.doctor.exception;

import com.doctor.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice

public class GlobalExceptionalHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> resourceNotFoundException(ResourceNotFoundException e, WebRequest webRequest){
                ErrorDetails errorDetails=new ErrorDetails(new Date(),e.getMessage(),webRequest.getDescription(false));
                        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);

    }

}
