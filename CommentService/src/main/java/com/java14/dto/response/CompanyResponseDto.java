package com.java14.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CompanyResponseDto {
    private String id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String logo;
    private String website;
    private String sector;

}
