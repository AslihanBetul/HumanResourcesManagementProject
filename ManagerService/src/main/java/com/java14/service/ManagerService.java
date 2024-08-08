package com.java14.service;

import com.java14.dto.request.CompanyIdRequestDto;
import com.java14.dto.request.SaveCompanyRequestDto;
import com.java14.dto.request.SaveManagerRequestDto;
import com.java14.entity.Manager;
import com.java14.manager.AuthManager;
import com.java14.manager.CompanyManager;
import com.java14.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManagerService {
    private final ManagerRepository managerRepository;
    private final AuthManager authManager;
    private final CompanyManager companyManager;

    public Boolean saveManager(SaveManagerRequestDto dto) {
        String name= dto.getCompany();
        String companyId = companyManager.companyId(name);
        Manager manager = Manager.builder().authId(dto.getId()).name(dto.getName()).surname(dto.getSurname())
                .email(dto.getEmail()).address(dto.getAddress()).taxNumber(dto.getTaxNumber())
                .phone(dto.getPhone()).companyId( companyId).build();
        managerRepository.save(manager);
        return true;

    }

    public Boolean deleteManager(String id) {
        Manager manager = managerRepository.findById(id).get();
        Long authId = manager.getAuthId();
        managerRepository.delete(manager);
       authManager.deleteAuth(authId);
        return true;
    }

    public List<Manager> getListManager() {
        return managerRepository.findAll();
    }
}
