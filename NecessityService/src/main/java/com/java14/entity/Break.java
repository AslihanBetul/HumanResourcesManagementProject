package com.java14.entity;

import com.java14.utility.enums.EBreakType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_break")
public class Break extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private EBreakType breakType;
    private String companyId;
    private Long managerId;
    private Long startTime;
    private Long endTime;
}
