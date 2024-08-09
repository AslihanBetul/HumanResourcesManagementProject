package com.java14.manager;

import com.java14.dto.response.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "http://localhost:9092/api/v1/manager", name = "managermanager",dismiss404 = true)
public interface ManagerManager {
    @GetMapping ("/getManagerIdFindByToken")
 String getManagerIdFindByToken(@RequestParam String token);
}
