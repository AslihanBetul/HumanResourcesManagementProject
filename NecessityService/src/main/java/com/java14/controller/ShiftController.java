package com.java14.controller;

import com.java14.constant.EndPoints;
import com.java14.dto.request.CreateShiftRequestDto;
import com.java14.dto.response.EmployeeShiftResponseDto;
import com.java14.dto.response.ResponseDto;
import com.java14.service.ShiftService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(EndPoints.SHIFT)
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT,RequestMethod.DELETE})
public class ShiftController {
    private final ShiftService shiftService;

    @PostMapping("/create-shift")
    public ResponseEntity<ResponseDto<Boolean>> createShift(@RequestBody CreateShiftRequestDto dto) {

        return ResponseEntity.ok(ResponseDto.<Boolean>builder()
                .data(shiftService.createShift(dto))
                .code(200)
                .message("Succesfully created")
                .build());
    }

    @GetMapping("/get-shift-by-employee-id")
    public ResponseEntity<ResponseDto<EmployeeShiftResponseDto>> getShiftByEmployeeId( String id) {
        return ResponseEntity.ok(ResponseDto.<EmployeeShiftResponseDto>builder()
                .data(shiftService.getShiftByEmployeeId(id))
                .code(200)
                .message("Succesfully ")
                .build());
    }

    @GetMapping("/get-shift-by-employee-id2")
    public EmployeeShiftResponseDto getShiftByEmployeeId2(@RequestParam String id) {
        return shiftService.getShiftByEmployeeId2(id);
    }
    @PostMapping("/delete-shift")
    public ResponseEntity<ResponseDto<Boolean>> deleteShift(String id) {
        return ResponseEntity.ok(ResponseDto.<Boolean>builder()
                .data(shiftService.deleteShift(id))
                .code(200)
                .message("Succesfully deleted")
                .build());
    }
}
