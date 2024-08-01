package com.java14.dto.response;

import com.java14.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class LoginResponseDto {
    Long id;
    String token;
    ERole role;
}
