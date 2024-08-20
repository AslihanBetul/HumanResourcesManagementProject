package com.java14.entity;

import com.java14.utility.enums.EEquipmentType;
import com.java14.utility.enums.EStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDate;

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
    private String employeeId;
    private String name;
    @Enumerated(EnumType.STRING)
    private EEquipmentType equipmentType;
    private String fixtureNo;
   // private Long authId;
    private String managerId;
    private String description;
    @Enumerated(EnumType.STRING)
    private EStatus status;
    @Builder.Default
    private LocalDate receivingDate = LocalDate.now();
    private LocalDate returningDate;
}
