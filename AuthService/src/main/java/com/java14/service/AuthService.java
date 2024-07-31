package com.java14.service;

import com.java14.dto.request.RegisterAdminRequestDto;
import com.java14.dto.request.RegisterEmployeeRequestDto;
import com.java14.dto.request.RegisterManagerRequestDto;
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
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;
    private final RabbitTemplate rabbitTemplate;
//admin register işlemleri

    public Boolean registerAdmin(RegisterAdminRequestDto dto) {

        checkEmailExist(dto.getEmail());

        Auth auth = AuthMapper.INSTANCE.RegisterAdminDtoToAuth(dto);
        auth.setRole(ERole.ADMIN);
        auth.setStatus(EStatus.ACTIVE);
        authRepository.save(auth);

        return true;

    }

// verilen emaili kontrol eder
    private void checkEmailExist(String email) {
        if(authRepository.existsByEmail(email)) {
            throw new AuthServiceException(EMAIL_ALREADY_TAKEN);
        }
    }

// manager kaydeder
    public Boolean registerManager(RegisterManagerRequestDto dto) {

        checkEmailExist(dto.getEmail());
        Auth auth = AuthMapper.INSTANCE.RegisterManagerDtoToAuth(dto);
        auth.setRole(ERole.MANAGER);
        auth.setStatus(EStatus.PENDING);
        auth.setPassword(CodeGenerator.generateCode());
        authRepository.save(auth);
     rabbitTemplate.convertAndSend("directExchange","keyManagerMail", ManagerSendMailModel.builder().email(dto.getEmail()).name(dto.getName()).password(auth.getPassword()).build());
        return true;
    }


    public Boolean registerEmployee(RegisterEmployeeRequestDto dto) {
        Auth auth = AuthMapper.INSTANCE.fromRegisterEmployeeRequestDtoToAuth(dto);
        auth.setBusinessEmail(dto.getName().toLowerCase() + "." + dto.getSurname().toLowerCase() + "@" + dto.getCompanyName().toLowerCase() + ".com");
        auth.setPassword(CodeGenerator.generateCode());
        auth.setRole(ERole.EMPLOYEE);
        auth.setStatus(EStatus.PENDING);
        authRepository.save(auth);
        rabbitTemplate.convertAndSend("directExchange","keyEmployeeMail", EmployeeSendMailModel.builder().personelEmail(dto.getPersonalEmail()).businessEmail(auth.getBusinessEmail()).name(dto.getName()).password(auth.getPassword()).companyName(dto.getCompanyName()).build());
        return true;
    }
}
