package com.java14.manager;



import com.java14.dto.response.EmployeeShiftResponseDto;
import com.java14.dto.response.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "http://localhost:9098/api/v1/shift", name = "shiftmanager",dismiss404 = true)
public interface ShiftManager {



    @GetMapping("/get-shift-by-employee-id2")
    EmployeeShiftResponseDto getShiftByEmployeeId2(@RequestParam String id);


}
