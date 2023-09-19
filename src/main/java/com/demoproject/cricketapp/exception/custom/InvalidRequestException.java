package com.demoproject.cricketapp.exception.custom;

import lombok.Data;

@Data
public class InvalidRequestException extends RuntimeException{
    private String errorMessage;

    public InvalidRequestException(String errorMessage) {
        super();
        this.errorMessage = errorMessage;
    }
    public InvalidRequestException() {

    }
}