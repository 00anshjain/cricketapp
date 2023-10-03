package com.demoproject.cricketapp.exception.custom;

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
