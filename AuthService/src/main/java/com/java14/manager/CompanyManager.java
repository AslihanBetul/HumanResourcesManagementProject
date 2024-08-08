package com.java14.manager;

import com.java14.dto.request.SaveCompanyRequestDto;
import com.java14.dto.response.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@FeignClient(url = "http://localhost:9093/api/v1/company", name = "companymanager",dismiss404 = true)
public interface CompanyManager {

    @PostMapping("/save-company")
     ResponseEntity<ResponseDto<Boolean>> saveCompany(@RequestBody SaveCompanyRequestDto dto);
}
