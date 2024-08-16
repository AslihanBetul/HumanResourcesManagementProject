package com.java14.controller;

import com.java14.dto.request.SaveEmployeeRequestDto;
import com.java14.dto.request.SaveManagerRequestDto;
import com.java14.dto.request.UpdateManagerRequestDto;
import com.java14.dto.response.*;
import com.java14.entity.Manager;
import com.java14.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static com.java14.constants.EndPoints.MANAGER;

@RestController
@RequestMapping(MANAGER)
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
public class ManagerController {
    private final ManagerService managerService;
    @PostMapping("/save-manager")
    public ResponseEntity<ResponseDto<Boolean>> saveManager(@RequestBody SaveManagerRequestDto dto) {

        return ResponseEntity.ok(ResponseDto.<Boolean>builder()
                .data(managerService.saveManager(dto))
                .code(200)
                .message("Succesfully registered")
                .build());
    }

    @PostMapping ("/delete-manager/{id}")
    public ResponseEntity<ResponseDto<Boolean>> deleteManager(@PathVariable String id) {

        return ResponseEntity.ok(ResponseDto.<Boolean>builder()
                .data(managerService.deleteManager(id))
                .code(200)
                .message("Succesfully deleted")
                .build());
    }
    @GetMapping("/manager-list")
    public ResponseEntity<ResponseDto<List<Manager>>> getAllManagers() {
        return ResponseEntity.ok(ResponseDto.<List<Manager>>builder()
                .data(managerService.getListManager())
                .code(200)
                .message("Manager list successfully")
                .build());
    }


    @PostMapping("/edit-manager")
    public ResponseEntity<ResponseDto<Boolean>> editManager(@RequestBody UpdateManagerRequestDto dto) {
        return ResponseEntity.ok(ResponseDto.<Boolean>builder()
                .data(managerService.editManager(dto))
                .code(200)
                .message("Succesfully edited")
                .build());
    }

    @GetMapping("/get-manager")
    public ResponseEntity<ResponseDto<GetManagerResponseDto>> getManager(String token) {
        return ResponseEntity.ok(ResponseDto.<GetManagerResponseDto>builder()
                .message("Succesfully get")
                .data(managerService.getManagerByToken(token))
                .code(200)
                .build());
    }


    @GetMapping ("/getManagerIdFindByToken")
    public String getManagerIdFindByToken(@RequestParam String token) {
        return managerService.getManagerIdFindByToken(token);
    }


    @GetMapping("/get-registration-end-date")
    public Boolean registrationEndDate(Integer days,String mail){
        return managerService.registrationEndDate(days,mail);
    }

    @GetMapping("/upcoming-company-dates")
    public ResponseEntity<ResponseDto<List<EndTimeManagerResponseDto>>> upcomingCompanyDates(){
        return ResponseEntity.ok(ResponseDto.<List<EndTimeManagerResponseDto>>builder()
                .message("Succesfully get")
                .data(managerService.getManagerByRegistrationEndDate())
                .code(200)
                .build());

    }

    @GetMapping("/get-manager-list-dto")
    public ResponseEntity<ResponseDto<List<ManagerResponseDto>>> getManagerListDto(){
        return ResponseEntity.ok(ResponseDto.<List<ManagerResponseDto>>builder()
                .message("Succesfully get")
                .data(managerService.getManagerList())
                .code(200)
                .build());
    }




}
