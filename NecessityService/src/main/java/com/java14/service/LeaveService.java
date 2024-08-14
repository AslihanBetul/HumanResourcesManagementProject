package com.java14.service;

import com.java14.dto.request.SaveLeaveRequestDto;
import com.java14.entity.Leave;
import com.java14.exception.NecessityServiceException;
import com.java14.manager.ManagerManager;
import com.java14.repository.LeaveRepository;
import com.java14.util.JwtTokenManager;
import com.java14.util.enums.EStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

import static com.java14.exception.ErrorType.INVALID_TOKEN;

@Service
@RequiredArgsConstructor
public class LeaveService {
    private final LeaveRepository leaveRepository;
    private final ManagerManager managerManager;
    private final JwtTokenManager jwtTokenManager;

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


}
