package com.java14.controller;

import static com.java14.constant.EndPoints.*;

import com.java14.dto.request.SaveEmployeeRequestDto;
import com.java14.dto.response.ResponseDto;
import com.java14.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
