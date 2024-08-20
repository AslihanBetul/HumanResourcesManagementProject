package com.java14.dto.response;

import com.java14.utility.enums.EShiftType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class EmployeeShiftResponseDto {
    String name;
    String surname;
    EShiftType shiftType;
    LocalDate startDate;
    LocalDate endDate;


}
