package com.demoproject.cricketapp.exception;

import com.demoproject.cricketapp.exception.custom.InvalidMatchRequestException;
import com.demoproject.cricketapp.exception.custom.InvalidUserInputException;
import com.demoproject.cricketapp.exception.custom.NoDataFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<String> handleNoDatFoundException(NoDataFoundException noDataFoundException){
        return new ResponseEntity<String>(noDataFoundException.getErrorMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidUserInputException.class)
    public ResponseEntity<String> handleInvalidUserInputException(InvalidUserInputException invalidUserInputException){
        return new ResponseEntity<String>(invalidUserInputException.getErrorMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidMatchRequestException.class)
    public ResponseEntity<String> handleInvalidMatchRequestException(InvalidMatchRequestException invalidMatchRequestException){
        return new ResponseEntity<String>(invalidMatchRequestException.getErrorMessage(), HttpStatus.BAD_REQUEST);
    }
}
