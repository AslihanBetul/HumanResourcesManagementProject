package com.java14.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class EmployeeBirthdayResponseDto {

    private String name;
    private String surname;
    private LocalDate birthDate;
    private String avatar;
}
