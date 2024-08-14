package com.java14.entity;

import com.java14.util.enums.EEquipmentType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "tbl_equipments")
public class Equipments extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long employeeId;
    private String name;
    private EEquipmentType equipmentType;
    private Long authId;
    private Long managerId;
    private String document;
    private Long receivingDate;
    private Long returningDate;
}
