package com.java14.dto.request;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class EditEmployeeRequestDto {
    private String token;
    private String id;
    private String name;
    private String surname;
    private String identityNumber;
    private String birthDate;
    @Email
    private String email;
    private String phoneNumber;
    private String address;
    private String driverLicense;
    private String avatar;


}
