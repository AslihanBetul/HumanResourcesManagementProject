package com.java14.service;

import com.java14.dto.request.RegisterAdminRequestDto;
import com.java14.dto.request.RegisterManagerRequestDto;
import com.java14.entity.Auth;
import com.java14.enums.ERole;
import com.java14.enums.EStatus;
import com.java14.exception.AuthServiceException;
import static com.java14.exception.ErrorType.*;

import com.java14.mapper.AuthMapper;
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


    public Boolean registerAdmin(RegisterAdminRequestDto dto) {

        checkEmailExist(dto.getEmail());

        Auth auth = AuthMapper.INSTANCE.RegisterAdminDtoToAuth(dto);
        auth.setRole(ERole.ADMIN);
        auth.setStatus(EStatus.ACTIVE);
        authRepository.save(auth);

        return true;

    }


    private void checkEmailExist(String email) {
        if(authRepository.existsByEmail(email)) {
            throw new AuthServiceException(EMAIL_ALREADY_TAKEN);
        }
    }


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
}
