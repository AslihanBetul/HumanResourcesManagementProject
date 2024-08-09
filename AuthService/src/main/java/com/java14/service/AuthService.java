package com.java14.service;

import com.java14.dto.request.*;
import com.java14.dto.response.LoginResponseDto;
import com.java14.dto.response.ResponseDto;
import com.java14.entity.Auth;
import com.java14.enums.ERole;
import com.java14.enums.EStatus;
import com.java14.exception.AuthServiceException;

import static com.java14.exception.ErrorType.*;

import com.java14.exception.ErrorType;
import com.java14.manager.*;
import com.java14.mapper.AuthMapper;

import com.java14.repository.AuthRepository;
import com.java14.utility.CodeGenerator;
import com.java14.utility.JwtTokenManager;
import com.java14.utility.enums.EEmailVerify;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import org.springframework.stereotype.Service;



import java.util.List;

import java.util.Optional;

@Service
@RequiredArgsConstructor

public class AuthService {

    private final AuthRepository authRepository;
    private final RabbitTemplate rabbitTemplate;
    private final JwtTokenManager jwtTokenManager;
    private final AdminManager adminManager;
    private final ManagerManager managerManager;
    private final CompanyManager companyManager;

    private final MailManager mailManager;
    private final EmployeeManager employeeManager;
//admin register işlemleri

    public Boolean registerAdmin(RegisterAdminRequestDto dto) {

        checkEmailExist(dto.getEmail());

        Auth auth = AuthMapper.INSTANCE.RegisterAdminDtoToAuth(dto);
        auth.setRole(ERole.ADMIN);
        auth.setStatus(EStatus.ACTIVE);
        auth.setEmailVerify(EEmailVerify.ACTIVE);
        authRepository.save(auth);
        SaveAdminRequestDto saveAdminRequestDto = SaveAdminRequestDto.builder()
                .id(auth.getId())
                .name(dto.getName())
                .surname(dto.getSurname())
                .email(dto.getEmail()).build();
        adminManager.saveAdmin(saveAdminRequestDto);

        return true;

    }

    // verilen emaili kontrol eder
    private void checkEmailExist(String email) {
        if (authRepository.existsByEmail(email)) {
            throw new AuthServiceException(EMAIL_ALREADY_TAKEN);
        }
    }

    // manager kaydeder
    public Boolean registerManager(RegisterManagerRequestDto dto) {
        checkEmailExist(dto.getEmail());
        Auth auth = AuthMapper.INSTANCE.RegisterManagerDtoToAuth(dto);
        auth.setEmail(dto.getEmail());
        auth.setRole(ERole.MANAGER);
        auth.setStatus(EStatus.PENDING);
        auth.setEmailVerify(EEmailVerify.INACTIVE);
        auth.setPassword(CodeGenerator.generateCode());
        companyManager.saveCompany(SaveCompanyRequestDto.builder().name(dto.getCompany()).build());
        // System.out.println(auth.getPassword()+" "+dto.getEmail());
        authRepository.save(auth);
        //rabbitTemplate.convertAndSend("directExchange", "keyManagerMail", ManagerSendMailModel.builder().name(dto.getName()).email(dto.getEmail()).password(auth.getPassword()).build());
        SaveManagerRequestDto saveManagerRequestDto
                = SaveManagerRequestDto.builder().id(auth.getId()).phone(dto.getPhone()).email(dto.getEmail()).company(dto.getCompany()).name(dto.getName()).surname(dto.getSurname()).address(dto.getAddress()).build();

        managerManager.saveManager(saveManagerRequestDto);

        return true;
    }


    public Boolean registerEmployee(RegisterEmployeeRequestDto dto) {
        Auth auth=Auth.builder().build();
        auth.setEmail(dto.getEmail());
        auth.setPassword(CodeGenerator.generateCode());
        auth.setRole(ERole.EMPLOYEE);
        auth.setStatus(EStatus.PENDING);
        auth.setEmailVerify(EEmailVerify.INACTIVE);
        authRepository.save(auth);

        String managerIdFindByToken = managerManager.getManagerIdFindByToken(dto.getManagerToken());
        SaveEmployeeRequestDto saveEmployeeRequestDto = SaveEmployeeRequestDto.builder().managerId(managerIdFindByToken).id(auth.getId()).email(dto.getEmail()).name(dto.getName())
               .surname(dto.getSurname()).companyName(dto.getCompanyName()).identityNumber(dto.getIdentityNumber())
               .phoneNumber(dto.getPhoneNumber()).address(dto.getAddress()).position(dto.getPosition()).companyName(dto.getCompanyName())
               .department(dto.getDepartment()).occupation(dto.getOccupation()).build();
       employeeManager.saveEmployee(saveEmployeeRequestDto);
        return true;
    }


    public LoginResponseDto login(LoginRequestDto dto) {
        Optional<Auth> optionalAuth = authRepository.findOptionalByEmailAndPassword(dto.getEmail(), dto.getPassword());

        if (optionalAuth.isEmpty()) {
            throw new AuthServiceException(USER_NOT_FOUND);
        }

        Auth auth = optionalAuth.get();

        if (auth.getStatus().equals(EStatus.ACTIVE)) {
            String token = jwtTokenManager.createToken(auth.getId()).orElseThrow(() -> new AuthServiceException(TOKEN_CREATION_FAILED));
            return LoginResponseDto.builder().id(auth.getId()).token(token).role(auth.getRole()).build();

        } else {
            throw new AuthServiceException(USER_IS_NOT_ACTIVE);
        }

    }


    public Boolean changePassword(ChangePaswordRequestDto dto) {
        Optional<Auth> optionalAuth = authRepository.findOptionalByEmailAndPassword(dto.getEmail(), dto.getPasswordByMail());
        if (optionalAuth.isEmpty()) {
            throw new AuthServiceException(USER_NOT_FOUND);
        }

        Auth auth = optionalAuth.get();
        auth.setPassword(dto.getNewPassword());
        auth.setStatus(EStatus.ACTIVE);
        authRepository.save(auth);
        return true;
    }

    public Boolean verifyEmail(VerifyEmailRequestDto dto) {
        System.out.println("Verify Email Request: " + dto.getEmail());
        Optional<Auth> optionalAuth = authRepository.findOptionalByEmail(dto.getEmail());
        System.out.println("metod çalıştı");
        if (optionalAuth.isEmpty()) {
            System.out.println("User not found for email: " + dto.getEmail());
            throw new AuthServiceException(USER_NOT_FOUND);
        }
        Auth auth = optionalAuth.get();
        auth.setEmailVerify(EEmailVerify.ACTIVE);
        authRepository.save(auth);
        return true;

    }


    public Boolean confirmManager(Long authId) {
        Optional<Auth> optionalAuth = authRepository.findById(authId);
        if (optionalAuth.isEmpty()) {
            throw new AuthServiceException(USER_NOT_FOUND);
        }

        Auth auth = optionalAuth.get();
        auth.setStatus(EStatus.ACTIVE);
        authRepository.save(auth);
        mailManager.sendInfoConfirmManager(auth.getEmail());
        return true;
    }

    public Integer getPendingNotificationCount() {
        return authRepository.countByStatusAndEmailVerify(EStatus.PENDING, EEmailVerify.ACTIVE);
    }

    public List<Auth> findAllByStatusAndEmailVerify() {
        return authRepository.findAllByStatusAndEmailVerify(EStatus.PENDING, EEmailVerify.ACTIVE);
    }

    public Boolean disconfirmManager(Long authId) {
        Optional<Auth> optionalAuth = authRepository.findById(authId);
        if (optionalAuth.isEmpty()) {
            throw new AuthServiceException(USER_NOT_FOUND);
        }
        Auth auth = optionalAuth.get();
        auth.setStatus(EStatus.PASSIVE);
        authRepository.save(auth);
        return true;
    }

    public Boolean deleteAuth(Long authId) {
        Optional<Auth> auth = authRepository.findById(authId);
        if (auth.isEmpty()) {
            throw new AuthServiceException(USER_NOT_FOUND);
        }else if (auth.get().getRole().equals(ERole.SUPER_ADMIN)) {
            throw new AuthServiceException(SUPER_ADMIN_CANNOT_BE_REMOVED);
        } else {
            auth.get().setStatus(EStatus.PASSIVE);
        }

        authRepository.save(auth.get());
        return true;
    }

    public String saveSuperAdmin(RegisterAdminRequestDto dto) {

        Auth auth = AuthMapper.INSTANCE.RegisterAdminDtoToAuth(dto);
        auth.setPassword(CodeGenerator.generateCode());
        auth.setEmail(dto.getEmail());
        auth.setRole(ERole.SUPER_ADMIN);
        auth.setStatus(EStatus.ACTIVE);
        auth.setEmailVerify(EEmailVerify.ACTIVE);
        authRepository.save(auth);
        adminManager.saveSuperAdmin(SaveSuperAdminRequestDto.builder().id(auth.getId()).role(ERole.SUPER_ADMIN).name(dto.getName()).surname(dto.getSurname()).email(dto.getEmail()).build());
        return auth.getEmail();
    }
}