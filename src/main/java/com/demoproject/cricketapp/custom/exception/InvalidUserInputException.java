package com.demoproject.cricketapp.custom.exception;

import lombok.Data;

@Data
public class InvalidUserInputException extends RuntimeException{
    private String errorMessage;

    public InvalidUserInputException(String errorMessage) {
        super();
        this.errorMessage = errorMessage;
    }
    public InvalidUserInputException() {

    }
}
