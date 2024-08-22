package com.java14.dto.response;

import com.java14.utility.enums.EEquipmentType;
import com.java14.utility.enums.EExpenseType;
import com.java14.utility.enums.EStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ExpensesResponseDto {

    Long id;
    private String employeeName;
    private String employeeSurname;
    private Double amount;
    private EExpenseType expenseType;
    private String description;
    private LocalDate expensesDate;
    private EStatus status;
    private String document;

}
