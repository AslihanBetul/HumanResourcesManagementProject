package com.java14.service;


import com.java14.dto.request.EditEmployeeRequestDto;
import com.java14.dto.request.SaveEmployeeRequestDto;
import com.java14.dto.request.UpdateEmployeeRequestDto;
import com.java14.dto.response.*;
import com.java14.entity.Employee;
import com.java14.exception.EmployeeServiceException;
import com.java14.exception.ErrorType;
import com.java14.manager.AuthManager;
import com.java14.manager.CompanyManager;
import com.java14.manager.ManagerManager;
import com.java14.manager.ShiftManager;
import com.java14.repository.EmployeeRepository;
import com.java14.utility.JwtTokenManager;
import com.java14.utility.enums.EStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.java14.exception.ErrorType.EMPLOYEE_NOT_FOUND;
import static com.java14.exception.ErrorType.USER_NOT_FOUND;


@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final JwtTokenManager jwtTokenManager;
    private final CompanyManager companyManager;
    private final AuthManager authManager;
    private final ManagerManager managerManager;
    private final ShiftManager shiftManager;



    public Boolean saveEmployee(SaveEmployeeRequestDto dto) {


        Employee employee = Employee.builder().authId(dto.getAuthId()).managerId(dto.getManagerId()).name(dto.getName()).surname(dto.getSurname())
                .email(dto.getEmail()).address(dto.getAddress()).identityNumber(dto.getIdentityNumber()).
                phoneNumber(dto.getPhoneNumber()).position(dto.getPosition()).jobStartDate(dto.getJobStartDate()).department(dto.getDepartment())
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
        Employee employee = employeeRepository.findById(dto.getId()).orElseThrow(() -> new EmployeeServiceException(EMPLOYEE_NOT_FOUND));

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
        Employee employee = employeeRepository.findByAuthId(authId).orElseThrow(() -> new EmployeeServiceException(EMPLOYEE_NOT_FOUND));

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
        Employee employee = employeeRepository.findByAuthId(authId).orElseThrow(() -> new EmployeeServiceException(EMPLOYEE_NOT_FOUND));
        return EditEmployeeResponseDto.builder()
                .id(employee.getId() != null ? employee.getId() : "")
                .name(employee.getName() != null ? employee.getName() : "")
                .surname(employee.getSurname() != null ? employee.getSurname() : "")
                .identityNumber(employee.getIdentityNumber() != null ? employee.getIdentityNumber() : "")
                .birthDate(employee.getBirthDate() != null ? employee.getBirthDate() : null)
                .email(employee.getEmail() != null ? employee.getEmail() : "")
                .phoneNumber(employee.getPhoneNumber() != null ? employee.getPhoneNumber() : "")
                .address(employee.getAddress() != null ? employee.getAddress() : "")
                .driverLicense(employee.getDriverLicense() != null ? employee.getDriverLicense() : "")
                .avatar(employee.getAvatar() != null ? employee.getAvatar() : "")
                .build();
    }

    public EmployeeAuthIdResponseDto getEmployeeByAuthId(Long authId) {
        Employee employee = employeeRepository.findByAuthId(authId).orElseThrow(() -> new EmployeeServiceException(EMPLOYEE_NOT_FOUND));

        return EmployeeAuthIdResponseDto.builder()
                .id(employee.getId())
                .name(employee.getName())
                .surname(employee.getSurname())
                .managerId(employee.getManagerId())
                .build();

    }
    public String getMailById(String id){
    Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeServiceException(EMPLOYEE_NOT_FOUND));
        String email = employee.getEmail();
        return email;

    }

    public Boolean activateEmployee( String id) {


        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeServiceException(EMPLOYEE_NOT_FOUND));

        Long employeeAuthId = employee.getAuthId();
        authManager.activateEmployee(employeeAuthId);
        employeeRepository.save(employee);
        return true;

    }

    public Boolean passivateEmployee( String id) {


        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeServiceException(EMPLOYEE_NOT_FOUND));

        Long employeeAuthId = employee.getAuthId();
        authManager.passivateEmployee(employeeAuthId);
        employeeRepository.save(employee);
        return true;

    }

    public List<DepartmanResponseDto>getDepartman(String token){
        String managerId = managerManager.getManagerIdFindByToken(token);
        List<Employee> employees = employeeRepository.findAllByManagerId(managerId);

        List<String> departmans = new ArrayList<>();
        employees.stream().forEach(employee -> {     String departman = employee.getDepartment();
            if (departman != null && !departman.isEmpty()) { departmans.add(departman); } });
        Map<String, Long> departmanCountMap = departmans.stream()
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()));

        List<DepartmanResponseDto> departmanList = departmanCountMap.entrySet().stream()
                .map(entry -> new DepartmanResponseDto(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        return departmanList;

    }

    public Integer getFemaleEmployeeCount(String token){
        String managerId = managerManager.getManagerIdFindByToken(token);

        return (int) employeeRepository.findAllByManagerId(managerId).stream()
                .filter(employee -> "FEMALE".equals(employee.getGender()))
                .count();

    }
    public Integer getMaleEmployeeCount(String token){
        String managerId = managerManager.getManagerIdFindByToken(token);
        return (int) employeeRepository.findAllByManagerId(managerId).stream()
                .filter(employee -> "MALE".equals(employee.getGender()))
                .count();

    }

    public Boolean yearsLeaveCountById(String id, Integer yearsLeave){
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeServiceException(EMPLOYEE_NOT_FOUND));
        int yearsLeave1 = employee.getYearsLeave();
        employee.setYearsLeave(yearsLeave1-yearsLeave);
        employeeRepository.save(employee);
        return true;
    }
    public int getYearsLeaveCountById(String id){
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeServiceException(EMPLOYEE_NOT_FOUND));
        return employee.getYearsLeave();
    }

    public List<EmployeeBirthdayResponseDto> getEmployeeBirthdays(String token){
        String managerId = managerManager.getManagerIdFindByToken(token);
        List<Employee> employeeList = employeeRepository.findAllByManagerId(managerId);
        List<EmployeeBirthdayResponseDto> birthdays = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (Employee employee : employeeList) {
                if(employee.getBirthDate() != null){
                    LocalDate birthDate = LocalDate.parse(employee.getBirthDate(), formatter);
                    EmployeeBirthdayResponseDto employeeBirthdayResponseDto= EmployeeBirthdayResponseDto
                            .builder()
                            .name(employee.getName())
                            .surname(employee.getSurname())
                            .avatar(employee.getAvatar())
                            .birthDate(birthDate)
                            .build();
                    birthdays.add(employeeBirthdayResponseDto);
                }


        }
        LocalDate bugun = LocalDate.now();

        birthdays.sort(Comparator.comparing(employee -> {
            LocalDate buYilDogumGunu = employee.getBirthDate().withYear(bugun.getYear());
            if (buYilDogumGunu.isBefore(bugun)) {
                buYilDogumGunu = buYilDogumGunu.plusYears(1);
            }
            return ChronoUnit.DAYS.between(bugun, buYilDogumGunu);
        }));


        return birthdays;
    }

    public List<EmployeeBirthdayResponseDto> getEmployeeBirthdays2(String token){
      Long employeeAuthId = jwtTokenManager.getIdFromToken(token).orElseThrow(() -> new EmployeeServiceException(EMPLOYEE_NOT_FOUND));
        Employee employee2 = employeeRepository.findByAuthId(employeeAuthId).orElseThrow(() -> new EmployeeServiceException(EMPLOYEE_NOT_FOUND));



        List<Employee> employeeList = employeeRepository.findAllByManagerId(employee2.getManagerId());
        List<EmployeeBirthdayResponseDto> birthdays = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (Employee employee : employeeList) {
            if(employee.getBirthDate() != null){
            LocalDate birthDate = LocalDate.parse(employee.getBirthDate(), formatter);
            EmployeeBirthdayResponseDto employeeBirthdayResponseDto= EmployeeBirthdayResponseDto
                    .builder()
                    .name(employee.getName())
                    .surname(employee.getSurname())
                    .avatar(employee.getAvatar())
                    .birthDate(birthDate)
                    .build();
            birthdays.add(employeeBirthdayResponseDto);}

        }
        LocalDate bugun = LocalDate.now();

        birthdays.sort(Comparator.comparing(employee -> {
            LocalDate buYilDogumGunu = employee.getBirthDate().withYear(bugun.getYear());
            if (buYilDogumGunu.isBefore(bugun)) {
                buYilDogumGunu = buYilDogumGunu.plusYears(1);
            }
            return ChronoUnit.DAYS.between(bugun, buYilDogumGunu);
        }));


        return birthdays;
    }


    public List<VardiyaResponseDto> getVardiyaList(String managerToken){
        String managerId = managerManager.getManagerIdFindByToken(managerToken);
        List<Employee> employeeList = employeeRepository.findAllByManagerId(managerId);
        List<VardiyaResponseDto> vardiyaList = new ArrayList<>();


        for (Employee employee : employeeList) {
            EmployeeShiftResponseDto shift = shiftManager.getShiftByEmployeeId2(employee.getId());

            VardiyaResponseDto vardiyaResponseDto = VardiyaResponseDto
                   .builder()
                   .id(employee.getId())
                   .name(employee.getName())
                   .surname(employee.getSurname())
                   .email(employee.getEmail())
                    .phoneNumber(employee.getPhoneNumber())
                    .position(employee.getPosition())
                    .department(employee.getDepartment())
                    .occupation(employee.getOccupation())
                    .gender(employee.getGender())
                    .shiftType(shift.getShiftType())
                    .startDate(shift.getStartDate())
                    .endDate(shift.getEndDate())
                   .build();
            vardiyaList.add(vardiyaResponseDto);
        }
        return vardiyaList;
    }


    public String getEmployeeName(String id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeServiceException(EMPLOYEE_NOT_FOUND));
        return employee.getName();
    }

    public String getEmployeeSurname(String id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeServiceException(EMPLOYEE_NOT_FOUND));
        return employee.getSurname();
    }

    public String getEmployeeIdByToken(String token) {
        Long employeeAuthId = jwtTokenManager.getIdFromToken(token).orElseThrow(() -> new EmployeeServiceException(EMPLOYEE_NOT_FOUND));
        Employee employee = employeeRepository.findByAuthId(employeeAuthId).orElseThrow(() -> new EmployeeServiceException(EMPLOYEE_NOT_FOUND));
        return employee.getId();


    }

    public Boolean getSalaryWithAmount (String employeeId, Double amount){
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new EmployeeServiceException(EMPLOYEE_NOT_FOUND));
        Double salary = employee.getSalary();
       employee.setSalary(salary+amount);
        employeeRepository.save(employee);
        return true;
    }

    public Integer getTotalEmployeeCount(String token) {
        String managerId = managerManager.getManagerIdFindByToken(token);
        return employeeRepository.findAllByManagerId(managerId).size(); }


    public  Boolean managerEmployeePasive(String managerId) {
        List<Employee> employees = employeeRepository.findAllByManagerId(managerId);
        for (Employee employee : employees) {
           employee.setStatus(EStatus.PASSIVE);
            employeeRepository.save(employee);
            authManager.deleteAuth(employee.getAuthId());
        }
        return true;
    }
}

