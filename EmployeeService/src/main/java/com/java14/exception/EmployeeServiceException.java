package com.java14.exception;

import lombok.Getter;

@Getter
public class EmployeeServiceException extends RuntimeException{

    private final ErrorType errorType;
    public EmployeeServiceException(ErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public EmployeeServiceException(ErrorType errorType, String message){
        super(message);
        this.errorType = errorType;
    }

}
