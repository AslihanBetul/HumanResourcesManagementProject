package com.java14.repository;

import com.java14.dto.response.ResponseDto;
import com.java14.entity.Auth;
import com.java14.enums.EStatus;
import com.java14.utility.enums.EEmailVerify;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthRepository extends JpaRepository <Auth ,Long >{
    boolean existsByEmail(String email);

    //Optional<Auth> findOptionalByPersonalEmailAndPassword(String personalEmail, String password);


    Optional<Auth> findOptionalByEmailAndPassword(String email, String password);

    Optional<Auth> findOptionalById(Long authId);


    Optional<Auth> findOptionalByEmail(String email);

    Integer countByStatusAndEmailVerify(EStatus eStatus,EEmailVerify eEmailVerify);

    List<Auth> findAllByStatusAndEmailVerify(EStatus eStatus, EEmailVerify eEmailVerify);

}