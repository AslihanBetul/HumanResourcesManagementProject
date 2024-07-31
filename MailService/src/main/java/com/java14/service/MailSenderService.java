package com.java14.service;


import com.java14.rabbit.model.ManagerSendMailModel;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailSenderService {

    private final JavaMailSender javaMailSender;

   @RabbitListener(queues = "queueManagerMail")
    public void sendMailChangePassword(ManagerSendMailModel dto) {
        String activationLink = generateActivationLink();
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setFrom("assimhrm@gmail.com");
        mailMessage.setTo(dto.getEmail());
        mailMessage.setSubject("Welcome to ASSIM!"+dto.getName());
        mailMessage.setText("Your Account is activated. \n \n " +
                "Welcome to ASSIM! \n \n " +
                "Your password is : " + dto.getPassword() + "\n \n " +
                "Please change your password by clicking on the link below. \n \n " + "" + activationLink);

        javaMailSender.send(mailMessage);
    }




    public String generateActivationLink() {
        return "http://localhost:19090/change-password";


    }




}
