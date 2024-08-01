package com.java14.service;

import com.java14.dto.request.*;
import com.java14.dto.response.LoginResponseDto;
import com.java14.dto.response.ResponseDto;
import com.java14.entity.Auth;
import com.java14.enums.ERole;
import com.java14.enums.EStatus;
import com.java14.exception.AuthServiceException;
import static com.java14.exception.ErrorType.*;

import com.java14.mapper.AuthMapper;
import com.java14.rabbit.model.EmployeeSendMailModel;
import com.java14.rabbit.model.ManagerSendMailModel;
import com.java14.repository.AuthRepository;
import com.java14.utility.CodeGenerator;
import com.java14.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;
    private final RabbitTemplate rabbitTemplate;
    private final JwtTokenManager jwtTokenManager;
//admin register işlemleri

    public Boolean registerAdmin(RegisterAdminRequestDto dto) {

        checkEmailExist(dto.getPersonalEmail());

        Auth auth = AuthMapper.INSTANCE.RegisterAdminDtoToAuth(dto);
        auth.setRole(ERole.ADMIN);
        auth.setStatus(EStatus.ACTIVE);
        authRepository.save(auth);

        return true;

    }

// verilen emaili kontrol eder
    private void checkEmailExist(String email) {
        if(authRepository.existsByPersonalEmail(email)) {
            throw new AuthServiceException(EMAIL_ALREADY_TAKEN);
        }
    }

// manager kaydeder
    public Boolean registerManager(RegisterManagerRequestDto dto) {

        checkEmailExist(dto.getPersonalEmail());
        Auth auth = AuthMapper.INSTANCE.RegisterManagerDtoToAuth(dto);
        auth.setPersonalEmail(dto.getPersonalEmail());
        auth.setBusinessEmail(dto.getName().toLowerCase() + "." + dto.getSurname().toLowerCase() + "@" + dto.getCompany().toLowerCase() + ".com");
        auth.setRole(ERole.MANAGER);
        auth.setStatus(EStatus.PENDING);
        auth.setPassword(CodeGenerator.generateCode());
        authRepository.save(auth);
        rabbitTemplate.convertAndSend("directExchange","keyManagerMail", ManagerSendMailModel.builder().businessEmail(auth.getBusinessEmail()).personalEmail(dto.getPersonalEmail()).name(dto.getName()).password(auth.getPassword()).build());
        return true;
    }


    public Boolean registerEmployee(RegisterEmployeeRequestDto dto) {
        Auth auth = AuthMapper.INSTANCE.fromRegisterEmployeeRequestDtoToAuth(dto);
        auth.setBusinessEmail(dto.getName().toLowerCase() + "." + dto.getSurname().toLowerCase() + "@" + dto.getCompanyName().toLowerCase() + ".com");
        auth.setPassword(CodeGenerator.generateCode());
        auth.setRole(ERole.EMPLOYEE);
        auth.setStatus(EStatus.PENDING);
        authRepository.save(auth);
        System.out.println(auth.getPassword());
        rabbitTemplate.convertAndSend("directExchange","keyEmployeeMail", EmployeeSendMailModel.builder().personalEmail(dto.getPersonalEmail()).businessEmail(auth.getBusinessEmail()).name(dto.getName()).password(auth.getPassword()).companyName(dto.getCompanyName()).build());
        return true;
    }


    public String login(LoginRequestDto dto) {
        Optional<Auth> optionalAuth = authRepository.findOptionalByBusinessEmailAndPassword(dto.getBusinessEmail(), dto.getPassword());

        if (optionalAuth.isEmpty()) {
            throw new AuthServiceException(USER_NOT_FOUND);
        }

        Auth auth = optionalAuth.get();

        if (auth.getStatus().equals(EStatus.ACTIVE)){
            String token = jwtTokenManager.createToken(auth.getId()).orElseThrow(() -> new AuthServiceException(TOKEN_CREATION_FAILED));
            LoginResponseDto loginResponseDto = AuthMapper.INSTANCE.AuthToLoginResponseDto(auth);
            return  token;
        }else {
            throw new AuthServiceException(USER_IS_NOT_ACTIVE);
        }

    }

    public Boolean changePassword(ChangePaswordRequestDto dto) {
        Optional<Auth> optionalAuth = authRepository.findOptionalByBusinessEmailAndPassword(dto.getBusinessEmail(),dto.getPasswordByMail());
        if (optionalAuth.isEmpty()) {
            throw new AuthServiceException(USER_NOT_FOUND);
        }

        Auth auth = optionalAuth.get();
        auth.setPassword(dto.getNewPassword());
        auth.setStatus(EStatus.ACTIVE);
        authRepository.save(auth);
        return true;
    }
}
