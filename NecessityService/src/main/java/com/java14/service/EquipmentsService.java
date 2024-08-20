package com.java14.service;

import com.java14.dto.request.RejectedEquipmentRequestDto;
import com.java14.dto.request.SaveEquipmentRequestDto;
import com.java14.dto.response.EquipmentResponseDto;
import com.java14.entity.Equipments;
import com.java14.manager.EmployeeManager;
import com.java14.manager.ManagerManager;
import com.java14.repository.EquipmentsRepository;
import com.java14.utility.CodeGenerator;
import com.java14.utility.enums.EStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EquipmentsService {
    private final EquipmentsRepository equipmentsRepository;
    private final ManagerManager managerManager;
    private final EmployeeManager employeeManager;


    public Boolean saveEquipment(SaveEquipmentRequestDto dto) {
        String managerId = managerManager.getManagerIdFindByToken(dto.getToken());
        String fixtureNo = "assim"+CodeGenerator.generateCode();

        equipmentsRepository.save(Equipments.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                 .status(EStatus.PENDING)
                .equipmentType(dto.getEquipmentType())
                .employeeId(dto.getEmployeeId())
                .fixtureNo(fixtureNo)
                .managerId(managerId)
                .build());


return true;

    }

    public List<EquipmentResponseDto> getAllEquipments(String token) {
        String managerId = managerManager.getManagerIdFindByToken(token);


        Optional<List<Equipments>> equipments = equipmentsRepository.findAllByManagerIdAndStatus(managerId,EStatus.ACTIVE);


      List<EquipmentResponseDto> equipmentRequestDtoList = new ArrayList<>();
      if(equipments.isPresent()){
          equipments.get().forEach(e -> equipmentRequestDtoList.add(EquipmentResponseDto.builder()
                 .id(e.getId())
                 .name(e.getName())
                 .description(e.getDescription())
                 .equipmentType(e.getEquipmentType())
                 .employeeId(e.getEmployeeId())
                          .receivingDate(String.valueOf(e.getReceivingDate()))
                          .employeeName(employeeManager.getEmployeeNameById(e.getEmployeeId()))
                  .employeeSurname(employeeManager.getEmployeeSurnameById(e.getEmployeeId()))
                          .fixtureNo(e.getFixtureNo())
                 .build()));
      }
        return equipmentRequestDtoList;
    }

    public Boolean deleteEquipment(Long id) {
        Optional<Equipments> optionalEquipments = equipmentsRepository.findById(id);
        if (!optionalEquipments.isPresent()) {
            return false;
        }
        Equipments equipments = optionalEquipments.get();
        equipments.setStatus(EStatus.PASSIVE);
        equipmentsRepository.save(equipments);
        return true;
    }

    public List<EquipmentResponseDto> getMyEquipment(String token) {
       String employeeId = employeeManager.getEmployeeIdByToken(token);
        Optional<List<Equipments>> equipments = equipmentsRepository.findAllByEmployeeIdAndStatus(employeeId,EStatus.ACTIVE);
        List<EquipmentResponseDto> equipmentRequestDtoList = new ArrayList<>();
        if(equipments.isPresent()){
            equipments.get().forEach(e -> equipmentRequestDtoList.add(EquipmentResponseDto.builder()
                   .id(e.getId())
                   .name(e.getName())
                   .description(e.getDescription())
                   .equipmentType(e.getEquipmentType())
                   .employeeId(e.getEmployeeId())
                   .receivingDate(String.valueOf(e.getReceivingDate()))
                   .employeeName(employeeManager.getEmployeeNameById(e.getEmployeeId()))
                   .employeeSurname(employeeManager.getEmployeeSurnameById(e.getEmployeeId()))
                    .fixtureNo(e.getFixtureNo())
                   .build()));
        }

        return equipmentRequestDtoList ;
    }

    public List<EquipmentResponseDto> getPendingEquipment(String token) {
        String employeeId = employeeManager.getEmployeeIdByToken(token);
        Optional<List<Equipments>> equipments = equipmentsRepository.findAllByEmployeeIdAndStatus(employeeId,EStatus.PENDING);
        List<EquipmentResponseDto> equipmentRequestDtoList = new ArrayList<>();
        if(equipments.isPresent()){
            equipments.get().forEach(e -> equipmentRequestDtoList.add(EquipmentResponseDto.builder()
                    .id(e.getId())
                    .name(e.getName())
                    .description(e.getDescription())
                    .equipmentType(e.getEquipmentType())
                    .employeeId(e.getEmployeeId())
                    .receivingDate(String.valueOf(e.getReceivingDate()))
                    .employeeName(employeeManager.getEmployeeNameById(e.getEmployeeId()))
                    .employeeSurname(employeeManager.getEmployeeSurnameById(e.getEmployeeId()))
                    .fixtureNo(e.getFixtureNo())
                    .build()));
        }

        return equipmentRequestDtoList ;

    }

    public Boolean acceptEquipment(Long id) {
        Optional<Equipments> optionalEquipments = equipmentsRepository.findById(id);
        if (!optionalEquipments.isPresent()) {
            return false;
        }
        Equipments equipments = optionalEquipments.get();
        equipments.setStatus(EStatus.ACTIVE);
        equipmentsRepository.save(equipments);
        return true;
    }

    public Boolean rejectEquipment(RejectedEquipmentRequestDto dto) {
        Optional<Equipments> optionalEquipments = equipmentsRepository.findById(dto.getId());
        if (!optionalEquipments.isPresent()) {
            return false;
        }
        Equipments equipments = optionalEquipments.get();
        equipments.setStatus(EStatus.REJECTED);
        equipments.setDescription(dto.getDescription());
        equipmentsRepository.save(equipments);
        return true;
    }

    public Integer getPendingEquipmentCount(String token ){
       String employeeId = employeeManager.getEmployeeIdByToken(token);
        return equipmentsRepository.countByStatusAndEmployeeId(EStatus.PENDING,employeeId);
    }

    public List<EquipmentResponseDto> getDescriptionRejectedEquipment(String token) {
        String managerId = managerManager.getManagerIdFindByToken(token);
        Optional<List<Equipments>> equipments = equipmentsRepository.findAllByManagerIdAndStatus(managerId,EStatus.REJECTED);
        List<EquipmentResponseDto> equipmentRequestDtoList = new ArrayList<>();
        if(equipments.isPresent()){
            equipments.get().forEach(e -> equipmentRequestDtoList.add(EquipmentResponseDto.builder()
                    .id(e.getId())
                    .name(e.getName())
                    .description(e.getDescription())
                    .equipmentType(e.getEquipmentType())
                    .employeeId(e.getEmployeeId())
                    .receivingDate(String.valueOf(e.getReceivingDate()))
                    .employeeName(employeeManager.getEmployeeNameById(e.getEmployeeId()))
                    .employeeSurname(employeeManager.getEmployeeSurnameById(e.getEmployeeId()))
                    .fixtureNo(e.getFixtureNo())
                    .build()));
        }
        return equipmentRequestDtoList;

    }
}
