package com.java14.controller;

import static com.java14.constants.EndPoints.*;

import com.java14.dto.request.*;
import com.java14.dto.response.LoginResponseDto;
import com.java14.dto.response.ResponseDto;
import com.java14.entity.Auth;
import com.java14.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AUTH)
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT,RequestMethod.DELETE})
public class AuthController {
    private final AuthService authService;

    @PostMapping(REGISTER_ADMIN)

    public ResponseEntity<ResponseDto<Boolean>> registerAdmin(@RequestBody RegisterAdminRequestDto dto) {
        return ResponseEntity.ok(ResponseDto.<Boolean>builder()
                .data(authService.registerAdmin(dto))
                .code(200)
                .message("Succesfully registered")
                .build());
    }


    @PostMapping(REGISTER_MANAGER)
    public ResponseEntity<ResponseDto<Boolean>> registerManager(@RequestBody RegisterManagerRequestDto dto) {
        return ResponseEntity.ok(ResponseDto.<Boolean>builder()
                .data(authService.registerManager(dto))
                .code(200)
                .message("Succesfully registered")
                .build());
    }

    @PostMapping(REGISTER_EMPLOYEE)
    public ResponseEntity<ResponseDto<Boolean>> registerEmployee(@RequestBody RegisterEmployeeRequestDto dto) {
        return ResponseEntity.ok(ResponseDto.<Boolean>builder()
                .data(authService.registerEmployee(dto))
                .code(200)
                .message("Succesfully registered")
                .build());
    }

    @PutMapping(CHANGE_PASSWORD)
    public ResponseEntity<ResponseDto<Boolean>> changePassword(@RequestBody ChangePaswordRequestDto dto) {
        return ResponseEntity.ok(ResponseDto.<Boolean>builder()
                .data(authService.changePassword(dto))
                .code(200)
                .message("Succesfully activated")
                .build());
    }

    @PostMapping(LOGIN)
    public ResponseEntity<ResponseDto<LoginResponseDto>> login(@RequestBody LoginRequestDto dto) {
        return ResponseEntity.ok(ResponseDto.<LoginResponseDto>builder()
                .data(authService.login(dto))
                .code(200)
                .message("Succesfully logged in")
                .build());
    }



    @PostMapping("/verifyEmail")
    public ResponseEntity<ResponseDto<Boolean>> verifyEmail(@RequestBody VerifyEmailRequestDto dto) {
        return ResponseEntity.ok(ResponseDto.<Boolean>builder()
                .data(authService.verifyEmail(dto))
                .code(200)
                .message("Succesfully verified")
                .build());
    }
    @GetMapping("/pendingNotificationCount")
    public ResponseEntity<Integer> getPendingNotificationCount(){
        return ResponseEntity.ok(authService.getPendingNotificationCount());
    }
    @GetMapping("/pendingManagers")
    public ResponseEntity<ResponseDto<List<Auth>>> getPendingManagers() {
        return ResponseEntity.ok(ResponseDto.<List<Auth>>builder()
                .data(authService.findAllByStatusAndEmailVerify())
                .code(200)
                .message("Succesfully verified")
                .build());
    }
    @PostMapping("/confirmManager/{authId}")
    public ResponseEntity<ResponseDto<Boolean>> confirmManager(@PathVariable  Long authId) {
        return ResponseEntity.ok(ResponseDto.<Boolean>builder()
                .data(authService.confirmManager(authId))
                .code(200)
                .message("Succesfully changed")
                .build());
    }
    @PostMapping("/disconfirmManager/{authId}")
    public ResponseEntity<ResponseDto<Boolean>> disconfirmManager(@PathVariable Long authId) {
        return ResponseEntity.ok(ResponseDto.<Boolean>builder()
                .data(authService.disconfirmManager(authId))
                .code(200)
                .message("Succesfully changed")
                .build());
    }
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto<Boolean>> deleteAuth(@RequestParam Long authId) {
        return ResponseEntity.ok(ResponseDto.<Boolean>builder()
                .data(authService.deleteAuth(authId))
                .code(200)
                .message("Succesfully deleted")
                .build());
    }




}