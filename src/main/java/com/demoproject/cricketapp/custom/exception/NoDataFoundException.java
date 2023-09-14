package com.demoproject.cricketapp.custom.exception;

import lombok.Data;

@Data
public class NoDataFoundException extends RuntimeException{
    private String errorMessage;

    public NoDataFoundException(String errorMessage) {
        super();
        this.errorMessage = errorMessage;
    }
    public NoDataFoundException() {

    }

}
