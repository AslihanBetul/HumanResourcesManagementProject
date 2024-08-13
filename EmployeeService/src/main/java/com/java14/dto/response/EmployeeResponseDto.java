package com.java14.dto.response;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class EmployeeResponseDto {
    private String managerToken;
    private String id;
    private String name;
    private String surname;
    private String managerId;
    private String companyId;
    private String identityNumber;
    private String birthDate;
    @Email
    private String email;
    private String phoneNumber;
    private String address;
    private String jobStartDate;
    private String jobEndDate;
    private String position;
    private Double salary;
    private String department;
    private String occupation;
    private String gender;
    private Boolean militaryService;
    private String driverLicense;
    private String avatar;
    private String shiftId;
}
