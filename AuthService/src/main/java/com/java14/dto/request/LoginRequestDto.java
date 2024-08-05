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
public class LoginRequestDto {
    @Email(message = "Lutfen gecerli bir email adresi giriniz.")
    @NotNull
    String email;
    @NotNull
    String password;
}
