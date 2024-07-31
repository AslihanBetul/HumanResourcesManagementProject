package com.java14.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class RegisterEmployeeRequestDto {
    private String token;
    private Long managerId;
    @Column(length = 50, nullable = false)
    private String name;
    @Column(length = 50, nullable = false)
    private String surname;
    @Column(unique = true, nullable = false)
    private String identityNumber;
    @Column(unique = true, nullable = false)
    @Size(min = 11, max = 11 , message = "Telefon numarasi 11 haneli olmalidir")
    private String phoneNumber;
    private String address;
    private String position;
    private String department;
    private String occupation;
    private String companyName;
    private Long jobStartDate;
    private String personalEmail;

}
