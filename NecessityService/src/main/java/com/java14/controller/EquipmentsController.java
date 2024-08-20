package com.java14.controller;

import static com.java14.constant.EndPoints.*;

import com.java14.dto.request.RejectedEquipmentRequestDto;
import com.java14.dto.request.SaveEquipmentRequestDto;
import com.java14.dto.response.EquipmentResponseDto;
import com.java14.dto.response.ResponseDto;
import com.java14.service.EquipmentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT,RequestMethod.DELETE})
@RequiredArgsConstructor
@RequestMapping(EQUIPMENT)
@RestController
public class EquipmentsController {
    private final EquipmentsService equipmentsService;

    @PostMapping("/save-equipment")
    public ResponseEntity<ResponseDto<Boolean>> saveEquipment(@RequestBody SaveEquipmentRequestDto dto) {
        return ResponseEntity.ok(ResponseDto.<Boolean>builder()
               .data(equipmentsService.saveEquipment(dto))
               .code(200)
               .message("Succesfully saved")
               .build());

    }

    @GetMapping("/get-all-equipments")
    public ResponseEntity<ResponseDto<List<EquipmentResponseDto>>> getAllEquipments(@RequestParam String token) {
        return ResponseEntity.ok(ResponseDto.<List<EquipmentResponseDto>>builder()
                .data(equipmentsService.getAllEquipments(token))
                .code(200)
                .message("Succesfully fetched")
                .build());
    }
    @PostMapping("/delete-equipment")
    public ResponseEntity<ResponseDto<Boolean>> deleteEquipment(Long id) {
        return ResponseEntity.ok(ResponseDto.<Boolean>builder()
                .data(equipmentsService.deleteEquipment(id))
                .code(200)
                .message("Succesfully deleted")
                .build());
    }

   @GetMapping("/get-my-equipments")
    public ResponseEntity<ResponseDto<List<EquipmentResponseDto>>> getMyEquipment(@RequestParam String token) {
        return ResponseEntity.ok(ResponseDto.<List<EquipmentResponseDto>>builder()
               .data(equipmentsService.getMyEquipment(token))
               .code(200)
               .message("Succesfully fetched")
               .build());

    }

    @PostMapping("/accept-equipment")
    public ResponseEntity<ResponseDto<Boolean>> acceptEquipment(Long id) {
        return ResponseEntity.ok(ResponseDto.<Boolean>builder()
                .data(equipmentsService.acceptEquipment(id))
                .code(200)
                .message("Succesfully accepted")
                .build());
    }


    @GetMapping("/get-pending-equipments")
    public ResponseEntity<ResponseDto<List<EquipmentResponseDto>>> getPendingEquipment(@RequestParam String token) {
        return ResponseEntity.ok(ResponseDto.<List<EquipmentResponseDto>>builder()
               .data(equipmentsService.getPendingEquipment(token))
               .code(200)
               .message("Succesfully fetched")
               .build());
    }

    @GetMapping("/get-pending-equipment-count")
    public ResponseEntity<Integer> getPendingEquipmentCount(@RequestParam String token) {
        return ResponseEntity.ok(equipmentsService.getPendingEquipmentCount(token));
    }

    @PostMapping("/reject-equipment")
    public ResponseEntity<ResponseDto<Boolean>> rejectEquipment(@RequestBody RejectedEquipmentRequestDto dto) {
        return ResponseEntity.ok(ResponseDto.<Boolean>builder()
                .data(equipmentsService.rejectEquipment(dto))
                .code(200)
                .message("Succesfully rejected")
                .build());
    }

    @GetMapping("/get-description-rejected-equipment")
    public ResponseEntity<ResponseDto<List<EquipmentResponseDto>>> getDescriptionRejectedEquipment(@RequestParam String token) {
        return ResponseEntity.ok(ResponseDto.<List<EquipmentResponseDto>>builder()
                .data(equipmentsService.getDescriptionRejectedEquipment(token))
                .code(200)
                .message("Succesfully fetched")
                .build());
    }







}

