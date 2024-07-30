package com.java14.exception;

import lombok.Getter;

@Getter
public class CommentServiceException extends RuntimeException {
    private final ErrorType errorType;
    public CommentServiceException(ErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public CommentServiceException(ErrorType errorType, String message){
        super(message);
        this.errorType = errorType;
    }
}
