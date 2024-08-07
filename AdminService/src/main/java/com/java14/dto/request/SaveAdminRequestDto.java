package com.java14.dto.request;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class SaveAdminRequestDto {
    private long id;
    private String name;
    private String surname;
    @Email
    private String email;



}
