package com.java14.dto.request;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class SaveEmployeeRequestDto {

private Long id;
    private String managerId;
     private String managerToken;
    private String name;
    private String companyName;
    private String surname;

    private String identityNumber;

    @Size(min = 11, max = 11 , message = "Telefon numarasi 11 haneli olmalidir")
    private String phoneNumber;
    private String address;
    private String position;
    private String department;
    private String occupation;

    private String jobStartDate;
    private String email;
}
