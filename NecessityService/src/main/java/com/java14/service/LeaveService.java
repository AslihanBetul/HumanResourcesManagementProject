package com.java14.service;

import com.java14.dto.request.LeaveRequestDto;
import com.java14.dto.request.SaveLeaveRequestDto;

import com.java14.dto.response.EmployeeAuthIdResponseDto;
import com.java14.dto.response.LeaveListResponseDto;
import com.java14.dto.response.LeaveResponseDto;
import com.java14.entity.Leave;

import com.java14.exception.NecessityServiceException;
import com.java14.manager.EmployeeManager;
import com.java14.manager.ManagerManager;
import com.java14.repository.LeaveRepository;
import com.java14.util.JwtTokenManager;
import com.java14.util.enums.EStatus;
import lombok.RequiredArgsConstructor;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


import static com.java14.exception.ErrorType.INVALID_TOKEN;
import static com.java14.exception.ErrorType.LEAVE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class LeaveService {
    private final LeaveRepository leaveRepository;
    private final ManagerManager managerManager;
    private final JwtTokenManager jwtTokenManager;
    private final EmployeeManager employeeManagerforLeave;
    private final RabbitTemplate rabbitTemplate;

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

        String mail = employeeManagerforLeave.getEmployeeByEmail(dto.getEmployeeId());
        employeeManagerforLeave.yearsLeaveCount(dto.getEmployeeId(),period.getDays());
        rabbitTemplate.convertAndSend("directExchange", "keyEmployeeMailleave",mail);
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

    public List<LeaveResponseDto> getPendingLeave(String token) {

        String managerId = managerManager.getManagerIdFindByToken(token);
        List<Leave> allByStatusAndManagerId = leaveRepository.findAllByStatusAndManagerId(EStatus.PENDING, managerId);

        return allByStatusAndManagerId.stream().map(leave -> LeaveResponseDto.builder()
                .id(leave.getId())
                .employeeId(leave.getEmployeeId())

                .managerId(leave.getManagerId())
                .name(leave.getName())
                .surname(leave.getSurname())
                .startDate(leave.getStartDate())
                .endDate(leave.getEndDate())
                .status(leave.getStatus())
                .leaveType(leave.getLeaveType())
                .numberOfDays(leave.getNumberOfDays())
                .description(leave.getDescription())
                .build()).toList();




    }
    public Integer getPendingLeaveCount(String token ){
        String managerId = managerManager.getManagerIdFindByToken(token);
        return leaveRepository.countByStatusAndManagerId(EStatus.PENDING,managerId);
    }
    public Boolean approveLeave(Long id)    {
        Leave leave = leaveRepository.findById(id).orElseThrow(() -> new NecessityServiceException(LEAVE_NOT_FOUND));
        leave.setStatus(EStatus.ACTIVE);
        leaveRepository.save(leave);
        employeeManagerforLeave.yearsLeaveCount(leave.getEmployeeId(),leave.getNumberOfDays());


        String mail =employeeManagerforLeave.getEmployeeByEmail(leave.getEmployeeId());
        rabbitTemplate.convertAndSend("directExchange", "keyApproveMailleave",mail);
        return true;
    }

    public Boolean disapproveLeave(Long id)    {
        Leave leave = leaveRepository.findById(id).orElseThrow(() -> new NecessityServiceException(LEAVE_NOT_FOUND));
        leave.setStatus(EStatus.PASSIVE);
        leaveRepository.save(leave);


        String mail =employeeManagerforLeave.getEmployeeByEmail(leave.getEmployeeId());
        rabbitTemplate.convertAndSend("directExchange", "keyDissapproveMailleave",mail);
        return true;
    }

    public List<LeaveListResponseDto>getAllMyLeave(String token) {
        Long authId = jwtTokenManager.getIdFromToken(token).orElseThrow(() -> new NecessityServiceException(INVALID_TOKEN));
       String employeeId= employeeManagerforLeave.getEmployeeByAuthId(authId).getId();
        List<Leave> myLeaveList = leaveRepository.findAllByEmployeeId(employeeId);
        List<LeaveListResponseDto> myLeaveListResponse = new ArrayList<>();
        myLeaveList.forEach(leave -> myLeaveListResponse.add(LeaveListResponseDto.builder()
                .startDate(leave.getStartDate())
               .endDate(leave.getEndDate())
                .leaveType(leave.getLeaveType())
                .build()));

        return myLeaveListResponse;

    }




}
