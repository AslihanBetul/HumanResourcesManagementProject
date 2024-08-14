package com.java14.service;

import com.java14.dto.request.LeaveRequestDto;
import com.java14.dto.request.SaveLeaveRequestDto;

import com.java14.dto.response.EmployeeAuthIdResponseDto;
import com.java14.entity.Leave;

import com.java14.exception.NecessityServiceException;
import com.java14.manager.EmployeeManager;
import com.java14.manager.ManagerManager;
import com.java14.repository.LeaveRepository;
import com.java14.util.JwtTokenManager;
import com.java14.util.enums.EStatus;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;


import static com.java14.exception.ErrorType.INVALID_TOKEN;

@Service
@RequiredArgsConstructor
public class LeaveService {
    private final LeaveRepository leaveRepository;
    private final ManagerManager managerManager;
    private final JwtTokenManager jwtTokenManager;
    private final EmployeeManager employeeManagerforLeave;

    public Boolean saveLeave(SaveLeaveRequestDto dto) {

        String managerId = managerManager.getManagerIdFindByToken(dto.getToken());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate tarihStart=null;
        LocalDate tarihEnd=null;
        try {
           String tarihStringStart = dto.getStartDate();
           String tarihStringEnd = dto.getEndDate();
           tarihStart = LocalDate.parse(tarihStringStart, formatter);
          tarihEnd= LocalDate.parse(tarihStringEnd, formatter);

           }
       catch (Exception e) { System.out.println("Tarih formatı hatalı: " + e.getMessage()); }

        Period period = Period.between(tarihStart, tarihEnd);


        leaveRepository.save(Leave.builder()
                .employeeId(dto.getEmployeeId())
                        .leaveType(dto.getLeaveType())
                        .startDate(tarihStart)
                        .endDate(tarihEnd)
                        .numberOfDays(period.getDays())
                        .name(dto.getName())
                        .surname(dto.getSurname())
                        .description(dto.getDescription())
                        .status(EStatus.ACTIVE)
                .managerId(managerId).build());
        return true;


    }


    public Boolean leaveRequest(LeaveRequestDto dto) {
        Long authId = jwtTokenManager.getIdFromToken(dto.getToken()).orElseThrow(() -> new NecessityServiceException(INVALID_TOKEN));



        EmployeeAuthIdResponseDto employeeByAuthId = employeeManagerforLeave.getEmployeeByAuthId(authId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate tarihStart=null;
        LocalDate tarihEnd=null;
        try {
            String tarihStringStart = dto.getStartDate();
            String tarihStringEnd = dto.getEndDate();
            tarihStart = LocalDate.parse(tarihStringStart, formatter);
            tarihEnd= LocalDate.parse(tarihStringEnd, formatter);

        }
        catch (Exception e) { System.out.println("Tarih formatı hatalı: " + e.getMessage()); }

        Period period = Period.between(tarihStart, tarihEnd);


        leaveRepository.save(Leave.builder()
                .employeeId(employeeByAuthId.getId())
                .name(employeeByAuthId.getName())
                .surname(employeeByAuthId.getSurname())
                .startDate(tarihStart)
                .endDate(tarihEnd)
                .leaveType(dto.getLeaveType())
                .description(dto.getDescription())
                .status(EStatus.PENDING)
                .numberOfDays(period.getDays())
                .managerId(employeeByAuthId.getManagerId()).build());
        return true;
    }
}
