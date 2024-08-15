package com.java14.service;


import com.java14.dto.request.EditEmployeeRequestDto;
import com.java14.dto.request.SaveEmployeeRequestDto;
import com.java14.dto.request.UpdateEmployeeRequestDto;
import com.java14.dto.response.EditEmployeeResponseDto;
import com.java14.dto.response.EmployeeAuthIdResponseDto;
import com.java14.dto.response.EmployeeResponseDto;
import com.java14.entity.Employee;
import com.java14.exception.EmployeeServiceException;
import com.java14.exception.ErrorType;
import com.java14.manager.AuthManager;
import com.java14.manager.CompanyManager;
import com.java14.manager.ManagerManager;
import com.java14.repository.EmployeeRepository;
import com.java14.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final JwtTokenManager jwtTokenManager;
    private final CompanyManager companyManager;
    private final AuthManager authManager;
    private final ManagerManager managerManager;



    public Boolean saveEmployee(SaveEmployeeRequestDto dto) {


        Employee employee = Employee.builder().authId(dto.getAuthId()).managerId(dto.getManagerId()).name(dto.getName()).surname(dto.getSurname())
                .email(dto.getEmail()).address(dto.getAddress()).identityNumber(dto.getIdentityNumber()).
                phoneNumber(dto.getPhoneNumber()).position(dto.getPosition()).department(dto.getDepartment())
                .occupation(dto.getOccupation()).companyId(companyManager.companyId(dto.getCompanyName())).build();
        employeeRepository.save(employee);
        return true;
    }


    public Boolean deleteEmployee(String id) {
        Employee employee = employeeRepository.findById(id).get();
        Long authId = employee.getAuthId();
        employeeRepository.delete(employee);
        authManager.deleteAuth(authId);

        return true;
    }

    public List<Employee> getListEmployee(String managerToken) {
        String managerId = managerManager.getManagerIdFindByToken(managerToken);
        return employeeRepository.findAllByManagerId(managerId);
    }

    public EmployeeResponseDto getEmployeeById(String id) {

        Employee employee = employeeRepository.findById(id).get();
        return EmployeeResponseDto.builder()
                .id(employee.getId())
                .name(employee.getName())
                .surname(employee.getSurname())
                .phoneNumber(employee.getPhoneNumber())
                .address(employee.getAddress())
                .position(employee.getPosition())
                .department(employee.getDepartment())
                .occupation(employee.getOccupation())
                .email(employee.getEmail())
                .birthDate(employee.getBirthDate())
                .avatar(employee.getAvatar())
                .managerId(employee.getManagerId())
                .companyId(employee.getCompanyId())
                .identityNumber(employee.getIdentityNumber())
                .jobStartDate(employee.getJobStartDate())
                .jobEndDate(employee.getJobEndDate())
                .salary(employee.getSalary())
                .gender(employee.getGender())
                .militaryService(employee.getMilitaryService())
                .driverLicense(employee.getDriverLicense())
                .shiftId(employee.getShiftId())
                .build();

    }

    public Boolean updateEmployee(UpdateEmployeeRequestDto dto) {
        Employee employee = employeeRepository.findById(dto.getId()).orElseThrow(() -> new EmployeeServiceException(ErrorType.EMPLOYEE_NOT_FOUND));

//      String managerIdFindByToken = managerManager.getManagerIdFindByToken(dto.getManagerToken());
//       if (!employee.getManagerId().equals(managerIdFindByToken)) {
//           throw new EmployeeServiceException(ErrorType.MANAGER_ID_DISMATCH);
//       }

        employee.setName(dto.getName() != null ? dto.getName() : employee.getName());
        employee.setSurname(dto.getSurname() != null ? dto.getSurname() : employee.getSurname());
        employee.setManagerId(dto.getManagerId() != null ? dto.getManagerId() : employee.getManagerId());
        employee.setCompanyId(dto.getCompanyId() != null ? dto.getCompanyId() : employee.getCompanyId());
        employee.setIdentityNumber(dto.getIdentityNumber() != null ? dto.getIdentityNumber() : employee.getIdentityNumber());
        employee.setBirthDate(dto.getBirthDate() != null ? dto.getBirthDate() : employee.getBirthDate());
        employee.setEmail(dto.getEmail() != null ? dto.getEmail() : employee.getEmail());
        employee.setPhoneNumber(dto.getPhoneNumber() != null ? dto.getPhoneNumber() : employee.getPhoneNumber());
        employee.setAddress(dto.getAddress() != null ? dto.getAddress() : employee.getAddress());
        employee.setJobStartDate(dto.getJobStartDate() != null ? dto.getJobStartDate() : employee.getJobStartDate());
        employee.setJobEndDate(dto.getJobEndDate() != null ? dto.getJobEndDate() : employee.getJobEndDate());
        employee.setPosition(dto.getPosition() != null ? dto.getPosition() : employee.getPosition());
        employee.setSalary(dto.getSalary() != null ? dto.getSalary() : employee.getSalary());
        employee.setDepartment(dto.getDepartment() != null ? dto.getDepartment() : employee.getDepartment());
        employee.setOccupation(dto.getOccupation() != null ? dto.getOccupation() : employee.getOccupation());
        employee.setGender(dto.getGender() != null ? dto.getGender() : employee.getGender());
        employee.setMilitaryService(dto.getMilitaryService() != null ? dto.getMilitaryService() : employee.getMilitaryService());
        employee.setDriverLicense(dto.getDriverLicense() != null ? dto.getDriverLicense() : employee.getDriverLicense());
        employee.setAvatar(dto.getAvatar() != null ? dto.getAvatar() : employee.getAvatar());
        employee.setShiftId(dto.getShiftId() != null ? dto.getShiftId() : employee.getShiftId());

        employeeRepository.save(Employee.builder()
                .id(employee.getId())
                .authId(employee.getAuthId())
                .name(employee.getName())
                .surname(employee.getSurname())
                .managerId(employee.getManagerId())
                .companyId(employee.getCompanyId())
                .identityNumber(employee.getIdentityNumber())
                .birthDate(employee.getBirthDate())
                .email(employee.getEmail())
                .phoneNumber(employee.getPhoneNumber())
                .address(employee.getAddress())
                .jobStartDate(employee.getJobStartDate())
                .jobEndDate(employee.getJobEndDate())
                .position(employee.getPosition())
                .salary(employee.getSalary())
                .department(employee.getDepartment())
                .occupation(employee.getOccupation())
                .gender(employee.getGender())
                .militaryService(employee.getMilitaryService())
                .driverLicense(employee.getDriverLicense())
                .avatar(employee.getAvatar())
                .shiftId(employee.getShiftId())
                .build());
        return true;
    }

    public Boolean editEmployee(EditEmployeeRequestDto dto) {
        Long authId = jwtTokenManager.getIdFromToken(dto.getToken()).orElseThrow(() -> new EmployeeServiceException(ErrorType.INVALID_TOKEN));
        Employee employee = employeeRepository.findByAuthId(authId).orElseThrow(() -> new EmployeeServiceException(ErrorType.EMPLOYEE_NOT_FOUND));

        employee.setName(dto.getName() != null ? dto.getName() : employee.getName());
        employee.setSurname(dto.getSurname() != null ? dto.getSurname() : employee.getSurname());
        employee.setIdentityNumber(dto.getIdentityNumber() != null ? dto.getIdentityNumber() : employee.getIdentityNumber());
        employee.setBirthDate(dto.getBirthDate() != null ? dto.getBirthDate() : employee.getBirthDate());
        employee.setEmail(dto.getEmail() != null ? dto.getEmail() : employee.getEmail());
        employee.setPhoneNumber(dto.getPhoneNumber() != null ? dto.getPhoneNumber() : employee.getPhoneNumber());
        employee.setAddress(dto.getAddress() != null ? dto.getAddress() : employee.getAddress());
        employee.setDriverLicense(dto.getDriverLicense() != null ? dto.getDriverLicense() : employee.getDriverLicense());
        employee.setAvatar(dto.getAvatar() != null ? dto.getAvatar() : employee.getAvatar());

        employeeRepository.save(Employee.builder()
                .id(employee.getId())
                .authId(employee.getAuthId())
                .name(employee.getName())
                .surname(employee.getSurname())
                .managerId(employee.getManagerId())
                .companyId(employee.getCompanyId())
                .identityNumber(employee.getIdentityNumber())
                .birthDate(employee.getBirthDate())
                .email(employee.getEmail())
                .phoneNumber(employee.getPhoneNumber())
                .address(employee.getAddress())
                .jobStartDate(employee.getJobStartDate())
                .jobEndDate(employee.getJobEndDate())
                .position(employee.getPosition())
                .salary(employee.getSalary())
                .department(employee.getDepartment())
                .occupation(employee.getOccupation())
                .gender(employee.getGender())
                .militaryService(employee.getMilitaryService())
                .driverLicense(employee.getDriverLicense())
                .avatar(employee.getAvatar())
                .shiftId(employee.getShiftId())
                .build());
        return true;
    }

    public EditEmployeeResponseDto getEmployeeByToken(String token) {
        Long authId = jwtTokenManager.getIdFromToken(token).orElseThrow(() -> new EmployeeServiceException(ErrorType.INVALID_TOKEN));
        Employee employee = employeeRepository.findByAuthId(authId).orElseThrow(() -> new EmployeeServiceException(ErrorType.EMPLOYEE_NOT_FOUND));
        return EditEmployeeResponseDto.builder()
                .id(employee.getId())
                .name(employee.getName())
                .surname(employee.getSurname())
                .identityNumber(employee.getIdentityNumber())
                .birthDate(employee.getBirthDate())
                .email(employee.getEmail())
                .phoneNumber(employee.getPhoneNumber())
                .address(employee.getAddress())
                .driverLicense(employee.getDriverLicense())
                .avatar(employee.getAvatar())
                .build();
    }

    public EmployeeAuthIdResponseDto getEmployeeByAuthId(Long authId) {
        Employee employee = employeeRepository.findByAuthId(authId).orElseThrow(() -> new EmployeeServiceException(ErrorType.EMPLOYEE_NOT_FOUND));

        return EmployeeAuthIdResponseDto.builder()
                .id(employee.getId())
                .name(employee.getName())
                .surname(employee.getSurname())
                .managerId(employee.getManagerId())
                .build();

    }
    public String getMailById(String id){
    Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeServiceException(ErrorType.EMPLOYEE_NOT_FOUND));
        String email = employee.getEmail();
        return email;

    }}
