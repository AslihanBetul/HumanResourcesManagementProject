package com.java14.entity;

import com.java14.utility.enums.EExpenseType;
import com.java14.utility.enums.EStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.sql.Blob;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "tbl_expenses")
public class Expenses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String employeeId;
    private String managerId;
    private Double amount;
    @Enumerated(EnumType.STRING)
    private EExpenseType expenseType;
    private String description;
    private LocalDate expensesDate;
    @Enumerated(EnumType.STRING)
    private EStatus status;
    private String document;
}
