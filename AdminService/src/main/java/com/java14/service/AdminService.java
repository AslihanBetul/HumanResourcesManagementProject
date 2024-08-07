package com.java14.service;

import com.java14.dto.request.SaveAdminRequestDto;


import com.java14.entity.Admin;
import com.java14.manager.AuthManager;
import com.java14.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final AuthManager authManager;


    public Boolean saveAdmin(SaveAdminRequestDto dto) {
        Admin admin = Admin.builder().authId(dto.getId()).name(dto.getName()).surname(dto.getSurname()).email(dto.getEmail()).build();

        adminRepository.save(admin);
        return true;
    }
    public Boolean deleteAdmin(String id) {
        Admin admin = adminRepository.findById(id).get();
        Long authId = admin.getAuthId();
        adminRepository.delete(admin);
        authManager.deleteAuth(authId);
        return true;
    }
    public List<Admin> getListAdmin (){
        return adminRepository.findAll();
    }
}
