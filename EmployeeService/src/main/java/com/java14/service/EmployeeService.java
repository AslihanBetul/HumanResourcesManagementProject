package com.java14.service;


import com.java14.dto.request.SaveEmployeeRequestDto;
import com.java14.entity.Employee;
import com.java14.manager.CompanyManager;
import com.java14.repository.EmployeeRepository;
import com.java14.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final JwtTokenManager jwtTokenManager;
    private final CompanyManager companyManager;



    public Boolean saveEmployee(SaveEmployeeRequestDto dto) {


        Employee employee = Employee.builder().authId(dto.getAuthId()).managerId(dto.getManagerId()).name(dto.getName()).surname(dto.getSurname())
                .email(dto.getEmail()).address(dto.getAddress()).identityNumber(dto.getIdentityNumber()).
                phoneNumber(dto.getPhoneNumber()).position(dto.getPosition()).department(dto.getDepartment())
                .occupation(dto.getOccupation()).companyId(companyManager.companyId(dto.getCompanyName())).build();
       employeeRepository.save(employee);
        return true;
    }
}
