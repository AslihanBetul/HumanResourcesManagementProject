package com.java14.controller;

import static com.java14.constant.EndPoints.*;

import com.java14.dto.request.SaveEmployeeRequestDto;
import com.java14.dto.request.UpdateEmployeeRequestDto;
import com.java14.dto.response.EmployeeResponseDto;
import com.java14.dto.response.ResponseDto;
import com.java14.entity.Employee;
import com.java14.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(EMPLOYEE)
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping("/save-employee")
    public ResponseEntity<ResponseDto<Boolean>> saveEmployee(@RequestBody SaveEmployeeRequestDto dto) {

        return ResponseEntity.ok(ResponseDto.<Boolean>builder()
                .data(employeeService.saveEmployee(dto))
                .code(200)
                .message("Succesfully registered")
                .build());
    }
    @PostMapping("/delete-employee/{id}")
    public ResponseEntity<ResponseDto<Boolean>> deleteEmployee(@PathVariable String id) {

        return ResponseEntity.ok(ResponseDto.<Boolean>builder()
                .data(employeeService.deleteEmployee(id))
                .code(200)
                .message("Succesfully deleted employee")
                .build());
    }

    @GetMapping("/employee-list")
    public ResponseEntity<ResponseDto<List<Employee>>> getAllEmployees() {
        return ResponseEntity.ok(ResponseDto.<List<Employee>>builder()
                .data(employeeService.getListEmployee())
                .code(200)
                .message("Employees retrieved successfully")
                .build());
    }

    @GetMapping("/get-employee-by-id")
    public ResponseEntity<ResponseDto<EmployeeResponseDto>> getEmployeeById(String id) {
        return ResponseEntity.ok(ResponseDto.<EmployeeResponseDto>builder()
                .data(employeeService.getEmployeeById(id))
                .code(200)
                .message("Admin retrieved successfully")
                .build());
    }

    @PostMapping("/update-employee")
    public ResponseEntity<ResponseDto<Boolean>> updateAdmin(@RequestBody UpdateEmployeeRequestDto dto) {
        return ResponseEntity.ok(ResponseDto.<Boolean>builder()
                .data(employeeService.updateEmployee(dto))
                .code(200)
                .message("Succesfully updated employee")
                .build());
    }
}
