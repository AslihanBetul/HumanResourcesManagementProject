package com.java14.repository;

import com.java14.dto.response.LeaveResponseDto;
import com.java14.entity.Leave;
import com.java14.util.enums.EStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRepository extends JpaRepository<Leave,Long> {
    List<Leave> findAllByStatusAndManagerId(EStatus eStatus, String managerId);

    Integer countByStatusAndManagerId(EStatus eStatus, String managerId);


}
