package com.demoproject.cricketapp.exception.custom;

import lombok.Data;

@Data
public class InvalidMatchRequestException extends RuntimeException{
    private String errorMessage;

    public InvalidMatchRequestException(String errorMessage) {
        super();
        this.errorMessage = errorMessage;
    }
    public InvalidMatchRequestException() {

    }
}
