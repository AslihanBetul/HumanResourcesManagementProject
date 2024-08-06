package com.java14.manager;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.messaging.MessagingException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "http://localhost:9097/api/v1/mail", name = "mailmanager",dismiss404 = true)
public interface MailManager {
    @PostMapping("/verify-account-manager")
     void sendMail(@RequestParam String email) throws MessagingException;
    @PostMapping("/confirm-manager")
    void sendInfoConfirmManager(@RequestParam String email) throws MessagingException;
}
