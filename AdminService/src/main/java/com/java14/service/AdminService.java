package com.java14.service;

import com.java14.dto.request.SaveAdminRequestDto;


import com.java14.dto.request.UpdateAdminRequestDto;
import com.java14.dto.response.AdminResponseDto;
import com.java14.entity.Admin;
import com.java14.exception.AdminServiceException;
import com.java14.exception.ErrorType;
import com.java14.manager.AuthManager;
import com.java14.repository.AdminRepository;
import com.java14.util.JwtTokenManager;
import com.java14.utilty.enums.ERole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final AuthManager authManager;
    private final JwtTokenManager jwtTokenManager;


    public Boolean saveAdmin(SaveAdminRequestDto dto) {
        Admin admin = Admin.builder().authId(dto.getId()).name(dto.getName()).surname(dto.getSurname()).email(dto.getEmail()).build();

        adminRepository.save(admin);
        return true;
    }

    public Boolean deleteAdmin(String id) {
        Admin admin = adminRepository.findById(id).get();
        Long authId = admin.getAuthId();
        if (admin.getRole().equals(ERole.SUPER_ADMIN)) {
            throw new AdminServiceException(ErrorType.SUPER_ADMIN_CANNOT_BE_REMOVED);
        }
        adminRepository.delete(admin);
        authManager.deleteAuth(authId);
        return true;
    }

    public List<Admin> getListAdmin() {
        return adminRepository.findAll();
    }


    public Boolean updateAdmin(UpdateAdminRequestDto dto) {
        Long authId = jwtTokenManager.getIdFromToken(dto.getToken()).orElseThrow(() -> new AdminServiceException(ErrorType.INVALID_TOKEN));

        Admin admin = adminRepository.findByAuthId(authId);

        admin.setName(dto.getName() != null ? dto.getName() : admin.getName());
        admin.setSurname(dto.getSurname() != null ? dto.getSurname() : admin.getSurname());
        admin.setEmail(dto.getEmail() != null ? dto.getEmail() : admin.getEmail());
        admin.setAddress(dto.getAddress() != null ? dto.getAddress() : admin.getAddress());
        admin.setPhone(dto.getPhone() != null ? dto.getPhone() : admin.getPhone());
        admin.setAvatar(dto.getAvatar() != null ? dto.getAvatar() : admin.getAvatar());
        adminRepository.save(Admin.builder()
                .id(admin.getId())
                .authId(authId)
                .name(admin.getName())
                .surname(admin.getSurname())
                .email(admin.getEmail())
                .address(admin.getAddress())
                .phone(admin.getPhone())
                .avatar(admin.getAvatar()).build());
        return true;
    }

    public AdminResponseDto getAdminByToken(String token) {
        Long authId = jwtTokenManager.getIdFromToken(token).orElseThrow(() -> new AdminServiceException(ErrorType.INVALID_TOKEN));
        Admin admin = adminRepository.findByAuthId(authId);
        return AdminResponseDto.builder()
                .name(admin.getName())
                .surname(admin.getSurname())
                .email(admin.getEmail())
                .address(admin.getAddress())
                .phone(admin.getPhone())
                .avatar(admin.getAvatar())
                .build();
    }
}
