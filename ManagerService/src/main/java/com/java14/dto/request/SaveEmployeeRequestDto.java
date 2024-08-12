package com.java14.dto.request;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class SaveEmployeeRequestDto {

private Long authId;
    private String managerId;
    private String managerToken;

    private String name;
    private String companyId;
    private String surname;
    private String address;
    private String email;

}
