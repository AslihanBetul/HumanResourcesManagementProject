package com.java14.service;

import com.java14.dto.request.*;
import com.java14.dto.response.GetManagerResponseDto;
import com.java14.entity.Manager;
import com.java14.exception.ErrorType;
import com.java14.exception.ManagerServiceException;
import com.java14.manager.AuthManager;
import com.java14.manager.CompanyManager;
import com.java14.repository.ManagerRepository;
import com.java14.util.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ManagerService {
    private final ManagerRepository managerRepository;
    private final AuthManager authManager;
    private final CompanyManager companyManager;
    private final JwtTokenManager jwtTokenManager;


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

    public Boolean editManager(UpdateManagerRequestDto dto) {
        Long authId = jwtTokenManager.getIdFromToken(dto.getToken()).orElseThrow(() -> new ManagerServiceException(ErrorType.INVALID_TOKEN));

        Manager manager = managerRepository.findByAuthId(authId);

        manager.setId(manager.getId());
        manager.setName(dto.getName()!=null?dto.getName():manager.getName());
        manager.setSurname(dto.getSurname()!=null?dto.getSurname():manager.getSurname());
        manager.setEmail(dto.getEmail()!=null?dto.getEmail():manager.getEmail());
        manager.setAddress(dto.getAddress()!=null?dto.getAddress():manager.getAddress());
        manager.setPhone(dto.getPhone()!=null?dto.getPhone():manager.getPhone());
        manager.setBirthDate(dto.getBirthDate()!=null?dto.getBirthDate():manager.getBirthDate());
        manager.setAvatar(dto.getAvatar()!=null?dto.getAvatar():manager.getAvatar());
        manager.setGender(dto.getGender()!=null?dto.getGender():manager.getGender());
        managerRepository.save(Manager.builder()
                .id(manager.getId()).authId(authId)
                .name(manager.getName())
                .surname(manager.getSurname())
                .email(manager.getEmail())
                .address(manager.getAddress())
                .phone(manager.getPhone())
                .gender(manager.getGender())
                .birthDate(manager.getBirthDate())
                .avatar(manager.getAvatar()).build());
        return true;

    }

    public GetManagerResponseDto getManagerByToken(@RequestParam("token")String token) {
        Long authId = jwtTokenManager.getIdFromToken(token).orElseThrow(() -> new ManagerServiceException(ErrorType.INVALID_TOKEN));

        Manager manager = managerRepository.findByAuthId(authId);
        return GetManagerResponseDto.builder().name(manager.getName()).surname(manager.getSurname())
                .email(manager.getEmail()).address(manager.getAddress())
                .phone(manager.getPhone())
                .gender(manager.getGender())
                .birthDate(manager.getBirthDate())
                .avatar(manager.getAvatar()).id(manager.getId())
                .build();

    }

    public String getManagerIdFindByToken(String token) {
        Long authId = jwtTokenManager.getIdFromToken(token).orElseThrow(() -> new ManagerServiceException(ErrorType.INVALID_TOKEN));

        Manager manager = managerRepository.findByAuthId(authId);


        System.out.println(manager.getId());
        return manager.getId();

    }

    public Boolean registrationEndDate(Integer days,String mail){
        LocalDate today = LocalDate.now();
        LocalDate futureDate = today.plusDays(days);
        Manager manager = managerRepository.findByEmail(mail);
        manager.setRegistrationEndDate(futureDate);
        managerRepository.save(manager);

        return true;
    }


}
