package com.java14.manager;

import com.java14.dto.response.CompanyResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "http://localhost:9093/api/v1/company", name = "companymanager",dismiss404 = true)
public interface CompanyManager {

    @GetMapping("/companyId")
    String companyId(@RequestParam String name);

    @GetMapping("/find-by-manager-id")
    CompanyResponseDto findManagerById(@RequestParam String id);
}


