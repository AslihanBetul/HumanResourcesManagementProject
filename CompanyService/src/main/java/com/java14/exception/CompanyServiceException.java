package com.java14.exception;

import lombok.Getter;

@Getter
public class CompanyServiceException extends RuntimeException {


    private final ErrorType errorType;

    public CompanyServiceException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public CompanyServiceException(ErrorType errorType, String message){
        super(message);
        this.errorType = errorType;
    }


}
