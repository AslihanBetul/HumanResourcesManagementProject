package com.java14.entity;

import com.java14.util.enums.EExpenseType;
import com.java14.util.enums.EStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tbl_expenses")
public class Expenses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private Long employeeId;
    private Long managerId;
    private Double amount;
    private EExpenseType expenseType;
    private String description;
    private Long requestDate;
    private Long approvalDate;
    private EStatus status;
}
