package com.java14.dto.response;

import com.java14.util.enums.ELeaveType;
import com.java14.util.enums.EStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class LeaveResponseDto {
    private Long id;
    private String employeeId;

    private String managerId;
    private String name;
    private String surname;
    private LocalDate startDate;
    private LocalDate endDate;
    @Enumerated(EnumType.STRING)
    private EStatus status;
    @Enumerated(EnumType.STRING)
    private ELeaveType leaveType;
    private Integer numberOfDays;
    private String description;
}
