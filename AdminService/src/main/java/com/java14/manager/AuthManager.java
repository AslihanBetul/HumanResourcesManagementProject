package com.java14.manager;

import com.java14.dto.request.SaveAdminRequestDto;
import com.java14.dto.response.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "http://localhost:9090/api/v1/auth", name = "authmanager",dismiss404 = true)
public interface AuthManager {

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto<Boolean>> deleteAuth(@RequestParam Long authId);
}
