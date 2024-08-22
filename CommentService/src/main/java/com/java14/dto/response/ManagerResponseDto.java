package com.java14.dto.response;

import com.java14.utility.enums.EGender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ManagerResponseDto {

    private String name;
    private String surname;
    private String avatar;
    private String birthDate;
    private String phone;
    private String email;
    private String address;
    private String companyName;
    private EGender gender;
    private LocalDate registrationEndDate;


}
