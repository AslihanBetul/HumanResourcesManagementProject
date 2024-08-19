package com.java14.dto.request;

import com.java14.utility.enums.EShiftType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CreateShiftRequestDto {
    private String token;
    private String employeeId;
    private String name;
    private String surname;
    private EShiftType shiftType;
    private LocalDate startDate;
    private LocalDate endDate;
}
