package com.java14.controller;

import static com.java14.constants.EndPoint.*;

import com.java14.rabbit.model.EmployeeSendMailModel;
import com.java14.service.MailSenderService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(MAIL)

public class MailController {
    private final MailSenderService senderService;

    @PostMapping(VERIFY_ACCOUNT_MANAGER)
    public void sendMail(@RequestParam String email) throws MessagingException {
        senderService.verifyAccount(email);
    }
    @PostMapping("/confirm-manager")
    public void sendInfoConfirmManager(@RequestParam String email) throws MessagingException {
        senderService.sendInfoConfirmManager(email);
    }

    @PostMapping("/send-reset-email")
    public ResponseEntity<String> sendResetEmail(@RequestBody EmployeeSendMailModel model) {
        try {
           senderService.sendPasswordResetEmail(model);
            return ResponseEntity.ok("Şifre yenileme e-postası başarıyla gönderildi.");
        } catch (MessagingException e) {
            // Log the exception
            e.printStackTrace();
            return ResponseEntity.status(500).body("E-posta gönderilirken bir hata oluştu.");
        }
    }
}


