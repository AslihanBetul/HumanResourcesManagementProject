package com.java14.dto.response;

import com.java14.utility.enums.EShiftType;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class VardiyaResponseDto {

    private String id;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private String position;
    private String department;
    private String occupation;
    private String gender;
    private EShiftType shiftType;
    private LocalDate startDate;
    private LocalDate endDate;

}