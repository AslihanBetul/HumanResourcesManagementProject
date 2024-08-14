package com.java14.exception;

import lombok.Getter;



@Getter
public class NecessityServiceException extends RuntimeException{

    private final ErrorType errorType;

    public NecessityServiceException(ErrorType errorType, String customMessage){
        super(customMessage);
        this.errorType = errorType;
    }
    public NecessityServiceException(ErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }
}