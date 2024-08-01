package com.java14.controller;

import static com.java14.constants.EndPoints.*;

import com.java14.dto.request.*;
import com.java14.dto.response.LoginResponseDto;
import com.java14.dto.response.ResponseDto;
import com.java14.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AUTH)
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {RequestMethod.POST,RequestMethod.GET,RequestMethod.PUT})
public class AuthController {
    private final AuthService authService;
    @PostMapping(REGISTER_ADMIN)

    public ResponseEntity<ResponseDto<Boolean>> registerAdmin( @RequestBody  RegisterAdminRequestDto dto) {
        return ResponseEntity.ok(ResponseDto.<Boolean>builder()
                .data(authService.registerAdmin(dto))
                        .code(200)
                        .message("Succesfully registered")
                .build());
    }

    @PostMapping(REGISTER_MANAGER)
    public ResponseEntity<ResponseDto<Boolean>> registerManager( @RequestBody RegisterManagerRequestDto dto) {
        return ResponseEntity.ok(ResponseDto.<Boolean>builder()
                .data(authService.registerManager(dto))
                        .code(200)
                        .message("Succesfully registered")
                .build());
    }

    @PostMapping(REGISTER_EMPLOYEE)
    public ResponseEntity<ResponseDto<Boolean>> registerEmployee( @RequestBody RegisterEmployeeRequestDto dto) {
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
    public  ResponseEntity<ResponseDto<String>> login(@RequestBody LoginRequestDto dto){

        return ResponseEntity.ok(ResponseDto.<String>builder()
                .data(authService.login(dto))
                .code(200)
                .message("Succesfully logged in")
                .build());
    }



}
