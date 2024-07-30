package com.java14.exception;

import lombok.Getter;

@Getter
public class AdminServiceException extends RuntimeException {

    private final ErrorType errorType;

    public AdminServiceException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public AdminServiceException(ErrorType errorType, String message){
        super(message);
        this.errorType = errorType;
    }


}
