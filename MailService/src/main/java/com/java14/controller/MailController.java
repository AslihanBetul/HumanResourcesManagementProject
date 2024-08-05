package com.java14.controller;

import static com.java14.constants.EndPoint.*;
import com.java14.service.MailSenderService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(MAIL)

public class MailController {
    private final MailSenderService senderService;

    @PostMapping(VERIFY_ACCOUNT_MANAGER)
    public void sendMail( String email) throws MessagingException {
        senderService.verifyAccount(email);
    }

}
