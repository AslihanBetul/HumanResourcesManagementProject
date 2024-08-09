package com.java14.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class SaveManagerRequestDto {
    private long id;
    private String name;
    private String surname;
    @Email
    private String email;
    private String phone;
    @NotNull
    private String address;
    @NotNull
    private String company;
    @NotNull
    private String taxNumber;



}
