package com.java14.service;


import com.java14.rabbit.model.EmployeeSendMailModel;
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

        mailMessage.setTo(dto.getEmail());
        mailMessage.setSubject("Welcome to ASSIM!"+dto.getName());
        mailMessage.setText("Your Account is activated. \n \n " +
                "Welcome to ASSIM! \n \n " +
                "Your password is : " + dto.getPassword() + "\n \n " +
                "Please change your password by clicking on the link below. \n \n " + "" + activationLink);

        javaMailSender.send(mailMessage);
    }
    @RabbitListener(queues = "queueEmployeeMail")
    public void sendInfoAndMailChangePassword(EmployeeSendMailModel model) throws MessagingException {
        String activationLink = generateActivationLink();
        SimpleMailMessage mailMessage=new SimpleMailMessage();
       
        mailMessage.setTo(model.getPersonelEmail());
        mailMessage.setSubject("Welcome  !" +" "+ model.getName());
        mailMessage.setText("Your Account is activated. \n \n " +
                "Welcome to "+ model.getCompanyName() + " \n \n " +
                "Your mail adress is : " + model.getBusinessEmail() + "\n \n " +
                "Your password is : " + model.getPassword() + "\n \n " +
                "Please change your password by clicking on the link below. \n \n " + "" + activationLink);

        javaMailSender.send(mailMessage);

    }




    public String generateActivationLink() {
        return "http://localhost:19090/change-password";


    }




}
