package com.java14.controller;


import com.java14.dto.request.ExpensesRequestDto;
import com.java14.dto.response.ExpensesResponseDto;
import com.java14.dto.response.ResponseDto;
import com.java14.entity.Expenses;
import com.java14.service.ExpensesService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static com.java14.constant.EndPoints.EXPENSES;

@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT,RequestMethod.DELETE})
@RequiredArgsConstructor
@RequestMapping(EXPENSES)
@RestController
public class ExpensessController {
    private final ExpensesService expensesService;


    @PostMapping("/save-expenses")
    public ResponseEntity<ResponseDto<Boolean>> createExpense(@RequestBody ExpensesRequestDto dto) {
        return ResponseEntity.ok(ResponseDto.<Boolean>builder()
                .data(expensesService.saveExpenses(dto))
                .code(200)
                .message("Succesfully saved")
                .build());

    }
    @GetMapping("/get-list-expenses")
    public ResponseEntity<ResponseDto<List<ExpensesResponseDto>>> getListExpenses(@RequestParam String token) {
        return ResponseEntity.ok(ResponseDto.<List<ExpensesResponseDto>>builder()
                .data(expensesService.getExpensesList(token))
                .code(200)
                .message("Succesfully saved")
                .build());

    }
    @PostMapping("/confirm-expenses")
    public ResponseEntity<ResponseDto<Boolean>> confirmExpense(@RequestParam Long id) {
        return ResponseEntity.ok(ResponseDto.<Boolean>builder()
                .data(expensesService.confirmExpenses(id))
                .code(200)
                .message("Succesfully saved")
                .build());
    }

    @PostMapping("/reject-expenses")
    public ResponseEntity<ResponseDto<Boolean>> rejectExpense(@RequestParam Long id) {
        return ResponseEntity.ok(ResponseDto.<Boolean>builder()
                .data(expensesService.rejectExpenses(id))
                .code(200)
                .message("Succesfully saved")
                .build());
    }

    @GetMapping("/get-my-expenses-list-by-token")
    public ResponseEntity<ResponseDto<List<Expenses>>> getMyExpenses(@RequestParam String token) {
        return ResponseEntity.ok(ResponseDto.<List<Expenses>>builder()
                .data(expensesService.getExpensesListByEmployeeId(token))
                .code(200)
                .message("Succesfully saved")
                .build());
    }

    @GetMapping("/get-pending-expenses-count")
    public ResponseEntity<ResponseDto<Integer>> getPendingExpensesCount(@RequestParam String token) {
        return ResponseEntity.ok(ResponseDto.<Integer>builder()
                .data(expensesService.getPendingExpensesCount(token))
                .code(200)
                .message("Succesfully saved")
                .build());
    }




}

