package com.java14.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CompanyByManagerResponseDto {
    private String id;
    private String name;
    private String title;
    private String description;
    private String address;
    private String phone;
    private String email;
    private String website;
    private String logo;
    private String sector;
    private String taxNumber;
    private String taxOffice;
    private String mersisNo;
    private String vision;
    private String mission;
    private String country;
    private String city;
    private Integer employeeCount;
    private String founded;
    private String foundingYear;
    private String linkedin;
}
