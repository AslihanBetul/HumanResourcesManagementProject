package com.java14.entity;

import com.java14.util.enums.ELeaveType;
import com.java14.util.enums.EStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "tbl_leave")
public class Leave extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long employeeId;
    private Long authId;
    private Long managerId;
    private String companyId;
    private String employeeName;
    private String employeeSurname;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long approvalDate;
    private EStatus status;
    private ELeaveType leaveType;
    private Double numberOfDays;
    private String document;
}
