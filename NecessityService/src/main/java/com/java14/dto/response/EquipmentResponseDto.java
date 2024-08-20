package com.java14.dto.response;

import com.java14.utility.enums.EEquipmentType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class EquipmentResponseDto {

    private Long id;
    private String name;
    private String employeeName;
    private String employeeSurname;
    private String description;
    @Enumerated(EnumType.STRING)
    private EEquipmentType equipmentType;
    private String receivingDate;
    private String employeeId;
    private String fixtureNo;

}
