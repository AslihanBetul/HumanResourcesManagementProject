package com.java14.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UpdateAdminRequestDto {
    private String id;
    private String name;
    private String surname;
    private String email;
    private String address;
    private String phone;
    private String avatar;
    private String token;
}
