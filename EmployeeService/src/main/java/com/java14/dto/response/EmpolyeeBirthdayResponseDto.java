package com.java14.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class EmpolyeeBirthdayResponseDto {

    private String name;
    private String surname;
    private String birthDate;
    private String avatar;
}
