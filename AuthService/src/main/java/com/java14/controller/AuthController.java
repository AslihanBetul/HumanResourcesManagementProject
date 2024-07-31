package com.java14.controller;

import static com.java14.constants.EndPoints.*;

import com.java14.dto.request.RegisterAdminRequestDto;
import com.java14.dto.request.RegisterManagerRequestDto;
import com.java14.dto.request.ResponseDto;
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

}
