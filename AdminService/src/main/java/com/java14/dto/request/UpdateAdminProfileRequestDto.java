package com.java14.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UpdateAdminProfileRequestDto {
    private String token;
    private String name;
    private String surname;
    private String address;
    private String phone;
    private String avatar;


}
