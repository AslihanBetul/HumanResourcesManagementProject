package com.java14.repository;

import com.java14.entity.Shift;
import com.java14.utility.enums.EStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShiftRepository extends JpaRepository<Shift, Long> {
    Optional<Shift> findByEmployeeIdAndStatus(String employeeId, EStatus eStatus);

    List<Shift> findAllByEmployeeIdAndStatus(String employeeId, EStatus eStatus);
}
