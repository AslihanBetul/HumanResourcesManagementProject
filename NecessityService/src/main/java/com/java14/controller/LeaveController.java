package com.java14.controller;

import static com.java14.constant.EndPoints.*;

import com.java14.dto.request.SaveLeaveRequestDto;
import com.java14.dto.response.ResponseDto;
import com.java14.service.LeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping(LEAVE)
@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT,RequestMethod.DELETE})
public class LeaveController {
    private final LeaveService leaveService;

    @PostMapping(SAVE)
    public ResponseEntity<ResponseDto<Boolean>> saveLeave(@RequestBody SaveLeaveRequestDto dto) {
        return ResponseEntity.ok(ResponseDto.<Boolean>builder()
                .data(leaveService.saveLeave(dto))
                .code(200)
                .message("Succesfully saved")
                .build());
    }
}
