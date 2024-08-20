package com.java14.repository;

import com.java14.entity.Equipments;
import com.java14.utility.enums.EStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


public interface EquipmentsRepository  extends JpaRepository< Equipments,Long> {
    Optional<List<Equipments>> findAllByManagerIdAndStatus(String managerId, EStatus eStatus);

    Optional<List<Equipments>> findAllByEmployeeIdAndStatus(String employeeId, EStatus eStatus);

    Optional<Equipments> findByEmployeeId(String employeeId);

    Integer countByStatusAndEmployeeId(EStatus eStatus, String employeeId);
}
