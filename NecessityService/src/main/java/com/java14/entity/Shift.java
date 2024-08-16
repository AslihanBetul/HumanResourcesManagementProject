package com.java14.entity;

import com.java14.utility.enums.EShiftType;
import com.java14.utility.enums.EStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "tbl_shift")
public class Shift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private EShiftType shiftType;
    private String companyName;
    private Long managerId;
    private Long employeeId;
    private String employeeName;
    private String employeeSurname;
    private LocalDate startTime;
    private LocalDate endTime;
    private EStatus status;

}
