package com.java14.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ErrorType {

    INTERNAL_ERROR(3000, "Sunucuda beklenmeyen hata oluştu, lütfen tekrar deneyin", HttpStatus.INTERNAL_SERVER_ERROR),
    ERROR_DUPLICATE_USERNAME(3001, "Bu kullanıcı adı zaten kayıtlıdır. Lütfen değiştirerek tekrar deneyiniz.", HttpStatus.BAD_REQUEST),
    ERROR_INVALID_COMPANY_ID(3002, "Geçersiz şirket kimlik numarası girişi.", HttpStatus.BAD_REQUEST),
    ERROR_CREATE_TOKEN(3003, "Token oluşturma hatası, lütfen tekrar deneyiniz.", HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST_ERROR(3004,"Girilen parametreler hatalıdır, lütfen tekrar deneyin", HttpStatus.BAD_REQUEST),
    INVALID_TOKEN(3005,"Girdiğiniz token hatalıdır. Lütfen değiştirerek tekrar deneyiniz.",HttpStatus.UNAUTHORIZED),
    COMPANY_NOT_FOUND(3006, "Şirket bulunamadı.", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(3007, "Kullanıcı bulunamadı.", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED(3008,"Yetkisiz kullanıcı" ,HttpStatus.BAD_REQUEST );


    int code;
    String message;
    HttpStatus httpStatus;


}
