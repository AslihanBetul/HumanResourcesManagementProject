package com.java14.entity;

import com.java14.util.enums.ELeaveType;
import com.java14.util.enums.EStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;


import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "tbl_leave")
public class Leave extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String employeeId;
    private Long authId;
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
