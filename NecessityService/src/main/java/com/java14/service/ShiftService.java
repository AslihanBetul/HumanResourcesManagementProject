package com.java14.service;

import com.java14.dto.request.CreateShiftRequestDto;
import com.java14.dto.response.EmployeeShiftResponseDto;
import com.java14.dto.response.ResponseDto;
import com.java14.entity.Shift;
import com.java14.exception.ErrorType;
import com.java14.exception.NecessityServiceException;
import com.java14.manager.ManagerManager;
import com.java14.repository.ShiftRepository;
import com.java14.utility.enums.EStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShiftService {
    private final ShiftRepository shiftRepository;
    private final ManagerManager managerManager;


    public Boolean createShift(CreateShiftRequestDto dto) {
        String managerId = managerManager.getManagerIdFindByToken(dto.getToken());
        List<Shift> shiftList = shiftRepository.findAllByEmployeeIdAndStatus(dto.getEmployeeId(), EStatus.ACTIVE);

        if (!shiftList.isEmpty()) {
            throw new NecessityServiceException(ErrorType.SHIFT_ALREADY_EXISTS);
        }
        shiftRepository.save(Shift.builder()
                .managerId(managerId)
                .name(dto.getName())
                .surname(dto.getSurname())
                .employeeId(dto.getEmployeeId())
                .shiftType(dto.getShiftType())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .status(EStatus.ACTIVE)
                .build());
        return true;


    }

    public EmployeeShiftResponseDto getShiftByEmployeeId(String id) {
        Optional<Shift> optionalShift = shiftRepository.findByEmployeeIdAndStatus(id,EStatus.ACTIVE);

        if (!optionalShift.isPresent()) {
            return null;
        }
        Shift shift = optionalShift.get();

        return EmployeeShiftResponseDto.builder()
                .name(shift.getName())
                .surname(shift.getSurname())
                .shiftType(shift.getShiftType())
                .startDate(shift.getStartDate())
                .endDate(shift.getEndDate())
                .build();
    }

    public Boolean deleteShift(String id) {
        Optional<Shift> optionalShift = shiftRepository.findByEmployeeIdAndStatus(id, EStatus.ACTIVE);
        if (!optionalShift.isPresent()) {
            throw new NecessityServiceException(ErrorType.SHIFTS_NOT_FOUND);
        }
        Shift shift = optionalShift.get();
        shift.setStatus(EStatus.PASSIVE);
        shiftRepository.save(shift);
        return true;
    }
    public EmployeeShiftResponseDto getShiftByEmployeeId2(String id) {
        Optional<Shift> optionalShift = shiftRepository.findByEmployeeIdAndStatus(id, EStatus.ACTIVE);

        if (!optionalShift.isPresent()) {

            return EmployeeShiftResponseDto.builder().build();
        }
        Shift shift = optionalShift.get();

        return EmployeeShiftResponseDto.builder()
                .name(shift.getName())
                .surname(shift.getSurname())
                .shiftType(shift.getShiftType())
                .startDate(shift.getStartDate())
                .endDate(shift.getEndDate())
                .build();
    }

}
