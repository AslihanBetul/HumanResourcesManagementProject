package com.java14.manager;

import com.java14.dto.request.SaveManagerRequestDto;
import com.java14.dto.response.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(url = "http://localhost:9092/api/v1/manager", name = "managermanager",dismiss404 = true)
public interface ManagerManager {

    @PostMapping("/save-manager")
    ResponseEntity<ResponseDto<Boolean>> saveManager(@RequestBody SaveManagerRequestDto dto);

    @GetMapping ("/getManagerIdFindByToken")
    String getManagerIdFindByToken(@RequestParam String token);


}
