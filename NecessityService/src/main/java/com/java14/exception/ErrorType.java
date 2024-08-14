package com.java14.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorType {

    INTERNAL_SERVER_ERROR(6000, "Sunucu Hatasi",HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_TOKEN(6001,"Invalid token information",HttpStatus.BAD_REQUEST),
    NO_LEAVES_FOR_AN_EMPLOYEE(6002,"There is no leave for an employee",HttpStatus.BAD_REQUEST),
    BAD_REQUEST_ERROR(6003,"You have entered an invalid parameter",HttpStatus.BAD_REQUEST),
    ADD_LEAVE_ERROR(6004, "Kiralama gerçekleşmedi",HttpStatus.INTERNAL_SERVER_ERROR),
    LEAVE_NOT_FOUND(6005, "Leave cannot found.",HttpStatus.BAD_REQUEST),
    EMPLOYEE_NOT_FOUND(6006, "Employee cannot found.",HttpStatus.BAD_REQUEST),
    LEAVE_TYPE_NOT_FOUND(6007, "Leave type cannot found.",HttpStatus.BAD_REQUEST),
    LEAVE_DATE_NOT_VALID(6008, "Leave date is not valid.",HttpStatus.BAD_REQUEST),
    NO_PENDING_LEAVES_FOR_MANAGER(6009, "There is no pending leave for manager.",HttpStatus.BAD_REQUEST),
    MANAGER_NOT_FOUD(6010, "Manager cannot found.",HttpStatus.BAD_REQUEST),
    EXPENSES_NOT_FOUND(6011,"Expenses not found" ,HttpStatus.BAD_REQUEST ),
    EXPENSES_ALREADY_EXISTS(6012, "Expenses already exists" ,HttpStatus.BAD_REQUEST ),
    MANAGER_NOT_AUTHORIZED(6013,"Manager is not authorized" ,HttpStatus.BAD_REQUEST ),
    EQUIPMENTS_NOT_FOUND(6014,"Equipment not found", HttpStatus.BAD_REQUEST ),
    UNAUTHORIZED(6015,"Unauthorized" ,HttpStatus.BAD_REQUEST ),
    SHIFTS_NOT_FOUND(6016,"Shifts not found", HttpStatus.BAD_REQUEST ),
    ;

    private int code;
    private String message;
    private HttpStatus httpStatus;
}
