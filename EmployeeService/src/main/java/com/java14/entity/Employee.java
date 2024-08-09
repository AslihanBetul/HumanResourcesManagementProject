package com.java14.entity;

import com.java14.utility.enums.EStatus;

import jakarta.validation.constraints.Email;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Employee extends BaseEntity {
      @MongoId
    private String id;
    private String name;
    private String surname;
    private Long authId;
    private String managerId;
    private String companyId;


    private String identityNumber;
    private String birthDate;
    @Email
    private String email;
    private String phoneNumber;
    private String address;
    private String jobStartDate;
    private String jobEndDate;
    private String position;
    private Double salary;
    private String department;
    private String occupation;
    private String gender;
    private Boolean militaryService;
    private String driverLicense;
    private String avatar;
    private String shiftId;
    private EStatus status;


}
