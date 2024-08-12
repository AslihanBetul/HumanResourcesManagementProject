package com.java14.dto.response;

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
    private String name;
    private String surname;
    private String phoneNumber;
    private String address;
    private String position;
    private String department;
    private String occupation;
    private String jobStartDate;
    private String email;
    private String birthDate;
    private String avatar;
}
