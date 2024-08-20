package com.java14.dto.request;

import com.java14.utility.enums.EEquipmentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class SaveEquipmentRequestDto {


    private String employeeId;
    private String name;
    private EEquipmentType equipmentType;

    private String token;
    private String description;



}

