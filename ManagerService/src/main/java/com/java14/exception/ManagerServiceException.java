package com.java14.exception;

import lombok.Getter;

@Getter
public class ManagerServiceException extends RuntimeException{

    private final ErrorType errorType;
    public ManagerServiceException(ErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public ManagerServiceException(ErrorType errorType, String message){
        super(message);
        this.errorType = errorType;
    }

}
