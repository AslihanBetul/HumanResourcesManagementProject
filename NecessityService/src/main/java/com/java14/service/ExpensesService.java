package com.java14.service;

import com.java14.dto.request.ExpensesRequestDto;

import com.java14.dto.response.EmployeeAuthIdResponseDto;

import com.java14.dto.response.ExpensesResponseDto;

import com.java14.entity.Expenses;
import com.java14.manager.EmployeeManager;
import com.java14.manager.ManagerManager;

import com.java14.repository.ExpensesRepository;

import com.java14.utility.JwtTokenManager;
import com.java14.utility.enums.EStatus;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;


import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ExpensesService {

   private  final JwtTokenManager jwtTokenManager;
   private final  EmployeeManager employeeManager;
    private final ExpensesRepository expensesRepository;
    private final ManagerManager managerManager;






    public Boolean saveExpenses(ExpensesRequestDto dto){
       Long authId = jwtTokenManager.getIdFromToken(dto.getToken()).get();
       EmployeeAuthIdResponseDto employeee  =employeeManager.getEmployeeByAuthId(authId);
       Expenses expenses = Expenses.builder()
               .employeeId(employeee.getId())
               .managerId(employeee.getManagerId())
               .amount(dto.getAmount())
               .expenseType(dto.getExpenseType())
               .expensesDate(dto.getExpensesDate())
               .description(dto.getDescription())
               .document(dto.getDocument())
               .status(EStatus.PENDING)
               .build();
       expensesRepository.save(expenses);

       return true;
   }

    public List<ExpensesResponseDto> getExpensesList(String token) {
        String managerId = managerManager.getManagerIdFindByToken(token);
        List<Expenses> expensesList = expensesRepository.findAllByManagerIdAndStatus(managerId, EStatus.PENDING);
        List<ExpensesResponseDto> list = new ArrayList<>();
        expensesList.forEach(expenses -> {
            ExpensesResponseDto expensesResponseDto = ExpensesResponseDto.builder()
                    .id(expenses.getId())
                    .employeeName(employeeManager.getEmployeeNameById(expenses.getEmployeeId()))
                    .employeeSurname(employeeManager.getEmployeeSurnameById(expenses.getEmployeeId()))
                    .amount(expenses.getAmount())
                    .expenseType(expenses.getExpenseType())
                    .description(expenses.getDescription())
                    .expensesDate(expenses.getExpensesDate())
                    .status(expenses.getStatus())
                    .document(expenses.getDocument())
                    .build();
            list.add(expensesResponseDto);
        });
        return list.isEmpty() ? new ArrayList<>() : list;
    }


    public Boolean confirmExpenses(Long id){
        Optional<Expenses> expensesOptional = expensesRepository.findById(id);
        if (expensesOptional.isPresent()){
            Expenses expenses = expensesOptional.get();
            expenses.setStatus(EStatus.ACTIVE);
            expensesRepository.save(expenses);
            Double amount = expenses.getAmount();
            String employeeId = expenses.getEmployeeId();
            employeeManager.getSalaryWithAmount(employeeId, amount);

            return true;
        }
        return false;
    }

    public Boolean rejectExpenses(Long id){
        Optional<Expenses> expensesOptional = expensesRepository.findById(id);
        if (expensesOptional.isPresent()){
            Expenses expenses = expensesOptional.get();
            expenses.setStatus(EStatus.REJECTED);
            expensesRepository.save(expenses);
            return true;
        }
        return false;
    }

    public List<Expenses> getExpensesListByEmployeeId(String token) {
        String employeeId=employeeManager.getEmployeeIdByToken(token);
        List<Expenses> expensesList = expensesRepository.findAllByEmployeeId(employeeId);
        return expensesList.isEmpty() ? new ArrayList<>() : expensesList;
    }


}
