package com.java14.controller;

import static com.java14.constants.EndPoint.*;

import com.java14.dto.request.SaveAdminRequestDto;
import com.java14.dto.request.SaveSuperAdminRequestDto;
import com.java14.dto.request.UpdateAdminProfileRequestDto;
import com.java14.dto.request.UpdateAdminRequestDto;
import com.java14.dto.response.AdminResponseDto;
import com.java14.dto.response.ResponseDto;
import com.java14.entity.Admin;
import com.java14.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ADMIN)
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/save-admin")
    public ResponseEntity<ResponseDto<Boolean>> saveAdmin(@RequestBody SaveAdminRequestDto dto) {

        return ResponseEntity.ok(ResponseDto.<Boolean>builder()
                .data(adminService.saveAdmin(dto))
                .code(200)
                .message("Succesfully registered")
                .build());
    }
    @PostMapping("/save-super-admin")
    public ResponseEntity<ResponseDto<Boolean>> saveSuperAdmin(@RequestBody SaveSuperAdminRequestDto dto) {

        return ResponseEntity.ok(ResponseDto.<Boolean>builder()
                .data(adminService.saveSuperAdmin(dto))
                .code(200)
                .message("Succesfully registered")
                .build());
    }

    @PostMapping("/delete-admin/{id}")
    public ResponseEntity<ResponseDto<Boolean>> deleteAdmin(@PathVariable String id) {

        return ResponseEntity.ok(ResponseDto.<Boolean>builder()
                .data(adminService.deleteAdmin(id))
                .code(200)
                .message("Succesfully registered")
                .build());
    }
    @GetMapping("/admin-list")
    public ResponseEntity<ResponseDto<List<Admin>>> getAllAdmins() {
        return ResponseEntity.ok(ResponseDto.<List<Admin>>builder()
                .data(adminService.getListAdmin())
                .code(200)
                .message("Admins retrieved successfully")
                .build());
    }

    @PostMapping("/update-admin")
    public ResponseEntity<ResponseDto<Boolean>> updateAdmin(@RequestBody UpdateAdminRequestDto dto) {
        return ResponseEntity.ok(ResponseDto.<Boolean>builder()
                .data(adminService.updateAdmin(dto))
                .code(200)
                .message("Succesfully registered")
                .build());
    }

    @GetMapping("/get-admin-by-id")
    public ResponseEntity<ResponseDto<AdminResponseDto>> getAdminByID( String id) {
        return ResponseEntity.ok(ResponseDto.<AdminResponseDto>builder()
               .data(adminService.getAdminById(id))
               .code(200)
               .message("Admin retrieved successfully")
               .build());
    }
    @GetMapping("/get-admin-by-token")
    public ResponseEntity<ResponseDto<AdminResponseDto>> getAdminByToken( String token) {
        return ResponseEntity.ok(ResponseDto.<AdminResponseDto>builder()
                .data(adminService.getAdminByToken(token))
                .code(200)
                .message("Admin retrieved successfully")
                .build());
    }
    @PostMapping("/update-admin-profile")
    public ResponseEntity<ResponseDto<Boolean>> updateAdminProfile (UpdateAdminProfileRequestDto dto){
        return ResponseEntity.ok(ResponseDto.<Boolean>builder()
                .data(adminService.updateAdminProfile(dto))
                .code(200)
                .message("Admin retrieved successfully")
                .build());
    }


}
