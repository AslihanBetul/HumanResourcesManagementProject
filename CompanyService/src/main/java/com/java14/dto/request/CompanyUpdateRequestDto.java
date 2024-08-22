package com.java14.dto.request;

import com.java14.utility.enums.EMemberShipPlan;
import com.java14.utility.enums.EStatus;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CompanyUpdateRequestDto {
    private String token;
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
    private String founded;
    private String foundingYear;
    private String linkedin;


}
