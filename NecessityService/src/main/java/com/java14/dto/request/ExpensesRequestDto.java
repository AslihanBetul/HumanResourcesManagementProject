package com.java14.dto.request;

import com.java14.utility.enums.EExpenseType;
import com.java14.utility.enums.EShiftType;
import com.java14.utility.enums.EStatus;
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
public class ExpensesRequestDto {
    private String token;
    private Double amount;
    private EExpenseType expenseType;
    private String description;
    private LocalDate expensesDate;
    private String document;
}
