package com.java14.manager;

import com.java14.dto.request.SaveEmployeeRequestDto;
import com.java14.dto.response.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@FeignClient(url = "http://localhost:9094/api/v1/employee", name = "employeemanager")
public interface EmployeeManager {

    @PostMapping("/save-employee")
    ResponseEntity<ResponseDto<Boolean>> saveEmployee(@RequestBody SaveEmployeeRequestDto dto);
}

