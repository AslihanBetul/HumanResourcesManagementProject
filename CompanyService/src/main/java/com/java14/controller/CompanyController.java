package com.java14.controller;

import com.java14.dto.request.CompanyIdRequestDto;
import com.java14.dto.request.SaveCompanyRequestDto;
import com.java14.dto.response.CompanyResponseDto;
import com.java14.dto.response.ResponseDto;
import com.java14.dto.response.SectorDto;
import com.java14.entity.Company;
import com.java14.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.java14.constant.EndPoints.COMPANY;

@RestController
@RequiredArgsConstructor
@RequestMapping(COMPANY)
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
public class CompanyController {

    private final CompanyService companyService;
    @PostMapping("/save-company")
    public ResponseEntity<ResponseDto<Boolean>>saveCompany(@RequestBody SaveCompanyRequestDto dto){
        return ResponseEntity.ok(ResponseDto.<Boolean>builder()
                .data(companyService.saveCompany(dto))
                .code(200)
                .message("Succesfully saved")
                .build());
    }
    @PostMapping("/save-company2")
    public ResponseEntity<ResponseDto<Boolean>>saveCompany2(@RequestBody Company dto){
        return ResponseEntity.ok(ResponseDto.<Boolean>builder()
                .data(companyService.saveCompan2y(dto))
                .code(200)
                .message("Succesfully saved")
                .build());
    }


    @GetMapping("/companyId")
    public String companyId(@RequestParam String name){

        return companyService.listCompanyId(name);
    }

    @GetMapping("get-all-company")
    public ResponseEntity<ResponseDto<List<CompanyResponseDto>>> getAllCompany(){
        return ResponseEntity.ok(ResponseDto.<List<CompanyResponseDto>>builder()
               .data(companyService.getAllCompany())
               .code(200)
               .message("All Companies retrieved successfully")
               .build());
    }
    @GetMapping("/sectors")
    public ResponseEntity<ResponseDto<Map<String, Long>>> getSectors(){
        return ResponseEntity.ok(ResponseDto.<Map<String, Long>>builder()
               .data(companyService.getSectors())
               .code(200)
               .message("All Sectors retrieved successfully")
               .build());
    }




    }





