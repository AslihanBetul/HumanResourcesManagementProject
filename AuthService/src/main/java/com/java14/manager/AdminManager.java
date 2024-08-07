package com.java14.manager;

import com.java14.dto.request.SaveAdminRequestDto;
import com.java14.dto.response.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessagingException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "http://localhost:9091/api/v1/admin", name = "adminmanager",dismiss404 = true)
public interface AdminManager {

    @PostMapping("/save-admin")
    public ResponseEntity<ResponseDto<Boolean>> saveAdmin(@RequestBody SaveAdminRequestDto dto);
}
