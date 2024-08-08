package com.java14.dto.request;

import com.java14.util.enums.EGender;
import com.java14.util.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UpdateManagerRequestDto {

    private String id;
    private String name;
    private String surname;
    private String avatar;
    private String birthDate;
    private String phone;
    private String address;
    private EGender gender;
    private String email;
    private String token;

}
