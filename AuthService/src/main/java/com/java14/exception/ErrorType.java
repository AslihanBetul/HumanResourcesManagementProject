package com.java14.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ErrorType {

    INTERNAL_ERROR(1000,"Sunucuda beklenmeyen hata oluştu, lütfen tekrar deneyiniz",HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST_ERROR(1001,"Girilen parametreler hatalıdır. Lütfen düzelterek tekrar deneyiniz",HttpStatus.BAD_REQUEST),
    ERROR_DUPLICATE_USERNAME(1002,"Bu Kullanıcı adı zaten kayıtlıdır. Lütfen değiştirerek tekrar deneyiniz.",HttpStatus.BAD_REQUEST),
    ERROR_CREATE_TOKEN(1003,"Token oluşturma hatası. Lütfen değiştirerek tekrar deneyiniz.",HttpStatus.INTERNAL_SERVER_ERROR),
    ERROR_INVALID_LOGIN_PARAMETER(1004,"Kullanıcı adı ya da şifre hatalıdır. Lütfen düzelterek tekrar deneyiniz.",HttpStatus.BAD_REQUEST),
    ERROR_EMAIL_ISCOMPANY(1005,"Girdiğiniz email adresi şirket emaili olmalıdır. Lütfen değiştirerek tekrar deneyiniz.",HttpStatus.BAD_REQUEST),
    INVALID_TOKEN(1006,"Girdiğiniz token hatalıdır. Lütfen değiştirerek tekrar deneyiniz.",HttpStatus.UNAUTHORIZED),
    USER_NOT_FOUND(1007,"Kullanıcı bulunamadı. Lütfen değiştirerek tekrar deneyiniz.",HttpStatus.NOT_FOUND),
    USER_IS_NOT_ACTIVE(1008,"Kullanıcı aktif değildir.",HttpStatus.NOT_FOUND),
    PASSWORD_NOT_MATCH(1009,"Sifreler uyusmuyor. Lütfen değiştirerek tekrar deneyiniz.",HttpStatus.BAD_REQUEST),
    USER_ALREADY_EXISTS(1010,"Bu Kullanıcı adı zaten kayıtlıdır. Lütfen değiştirerek tekrar deneyiniz.",HttpStatus.BAD_REQUEST),
    PASSWORD_MISMATCH(1011,"Sifreler uyusmuyor. Lütfen değiştirerek tekrar deneyiniz.",HttpStatus.BAD_REQUEST),
    EMAIL_ALREADY_TAKEN(1012,"Bu email adresi zaten kayıtlıdır. Lütfen değiştirerek tekrar deneyiniz.",HttpStatus.BAD_REQUEST),
    TOKEN_CREATION_FAILED(1013,"Token oluşturma hatası. Lütfen değiştirerek tekrar deneyiniz.",HttpStatus.INTERNAL_SERVER_ERROR),

    SUPER_ADMIN_CANNOT_BE_REMOVED(1014,"Super admin silinemez" ,HttpStatus.BAD_REQUEST );


    int code;
    String message;
    HttpStatus httpStatus;
}