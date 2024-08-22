package com.java14.repository;


import com.java14.entity.Expenses;

import com.java14.utility.enums.EStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ExpensesRepository extends JpaRepository<Expenses,Long> {

    List<Expenses> findAllByManagerIdAndStatus(String managerId, EStatus eStatus);

    List<Expenses> findAllByEmployeeId(String employeeId);

}
