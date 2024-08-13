package com.java14.service;


import com.java14.rabbit.model.EmployeeSendMailModel;
import com.java14.rabbit.model.ManagerSendMailModel;
import com.java14.rabbit.model.VerifyEmailRequestDto;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
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
    public void sendMailVerifyEmail(ManagerSendMailModel dto) throws MessagingException {
        String activationLink = verifyAccountLink(dto.getEmail());
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");


        String htmlContent = "<html><body>" +
                "<table cellpadding=\"0\" cellspacing=\"0\" align=\"center\" width=\"770px\" style=\"font-family:Arial,sans-serif;color:#000000;background-color:#f8f8f8;font-size:14px;\">" +
                "    <tbody><tr>" +
                "        <td style=\"padding-top:60px;padding-right:70px;padding-bottom:60px;padding-left:70px\">" +
                "            <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" style=\"border-color:#e6e6e6;border-width:1px;border-style:solid;background-color:#fff;padding-top:25px;padding-right:0;padding-bottom:50px;padding-left:30px\">" +
                "                <tbody><tr>" +
                "                    <td style=\"padding:0 30px 30px\">" +
                "                        <p style=\"font-family:Arial,sans-serif;color:#000000;font-size:19px;margin-top:14px;margin-bottom:0\">" +
                "                            Merhaba, " + dto.getName() + "!" +
                "                        </p>" +
                "                        <p style=\"font-family:Arial,sans-serif;color:#000000;font-size:14px;line-height:17px;margin-top:30px;margin-bottom:0\">" +

                "                        </p>" +
                "                        <ul style=\"font-family:Arial,sans-serif;color:#000000;font-size:14px;line-height:17px;margin-top:10px;margin-bottom:0\">" +
                "                            <li style=\"font-family:Arial,sans-serif;color:#000000;font-size:14px;line-height:17px;margin-top:0;margin-bottom:5px\">" +
                "                                <b>Şifreniz: </b>" + dto.getPassword() + "<br>" +

                "                                <b>Emailinizi aktive etmek için aşağıdaki bağlantıya tıklayın: </b><a href=\"" + activationLink + "\">Email Aktifleştirme Bağlantısı</a>" +
                "                            </li>" +
                "                        </ul>" +
                "                    </td>" +
                "                </tr>" +
                "            </tbody></table>" +
                "            <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\">" +
                "                <tbody><tr>" +
                "                    <td style=\"padding-top:12px;\"></td>" +
                "                </tr>" +
                "                <tr>" +
                "                    <td style=\"font-family:Arial,sans-serif;font-size:12px;color:#888888;padding-right:30px;padding-left:30px\">" +
                "                        <img style=\"width: 300px;\"+ src=\"https://i.pinimg.com/736x/6e/ae/4a/6eae4a13af8db638a5e6bc344364646a.jpg\" alt=\"Company Logo\" />" +
                "                    </td>" +
                "                </tr>" +
                "            </tbody></table>" +
                "        </td>" +
                "    </tr></tbody></table>" +
                "</body></html>";

        helper.setText(htmlContent, true);
        helper.setTo(dto.getEmail());
        helper.setSubject("ASSIM'e Hoş Geldiniz, " + dto.getName() + "!");

        javaMailSender.send(mimeMessage);
    }

    @RabbitListener(queues = "queueEmployeeMail")
    public void sendInfoAndMailChangePassword(EmployeeSendMailModel model) throws MessagingException {
        String activationLink = verifyAccountLink(model.getEmail());
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");


        String htmlContent = "<html><body>" +
                "<table cellpadding=\"0\" cellspacing=\"0\" align=\"center\" width=\"770px\" style=\"font-family:Arial,sans-serif;color:#000000;background-color:#f8f8f8;font-size:14px;\">" +
                "    <tbody><tr>" +
                "        <td style=\"padding-top:60px;padding-right:70px;padding-bottom:60px;padding-left:70px\">" +
                "            <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" style=\"border-color:#e6e6e6;border-width:1px;border-style:solid;background-color:#fff;padding-top:25px;padding-right:0;padding-bottom:50px;padding-left:30px\">" +
                "                <tbody><tr>" +
                "                    <td style=\"padding:0 30px 30px\">" +
                "                        <p style=\"font-family:Arial,sans-serif;color:#000000;font-size:19px;margin-top:14px;margin-bottom:0\">" +
                "                            Merhaba, " + model.getName() + "!" +
                "                        </p>" +

                "                        </p>" +
                "                        <ul style=\"font-family:Arial,sans-serif;color:#000000;font-size:14px;line-height:17px;margin-top:10px;margin-bottom:0\">" +
                "                            <li style=\"font-family:Arial,sans-serif;color:#000000;font-size:14px;line-height:17px;margin-top:0;margin-bottom:5px\">" +
                "                                <b>Şirket Adı: </b>" + model.getCompanyName() + "<br>" +

                "                                <b>Şifreniz: </b>" + model.getPassword() + "<br>" +
                "                                <b>Şifrenizi değiştirmek için lütfen aşağıdaki bağlantıya tıklayın: </b><a href=\"" + activationLink + "\">Email aktivasyon bağlantısı</a>" +
                "                            </li>" +
                "                        </ul>" +
                "                    </td>" +
                "                </tr>" +
                "            </tbody></table>" +
                "            <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\">" +
                "                <tbody><tr>" +
                "                    <td style=\"padding-top:12px;\"></td>" +
                "                </tr>" +
                "                <tr>" +
                "                    <td style=\"font-family:Arial,sans-serif;font-size:12px;color:#888888;padding-right:30px;padding-left:30px\"> +</td>" +
                "                </tr>" +
                "      <img style=\"width: 300px;\" src =https://media.licdn.com/dms/image/C560BAQG6YMo64tCAYA/company-logo_200_200/0/1631433952841/linkedinik_logo?e=2147483647&v=beta&t=-TEZ-pxfUxLqkeZCsjdAG_jFm-YSel8YZuqdJujSHX0 />" +
                "            </tbody></table>" +
                "        </td>" +
                "    </tr></tbody></table>" +
                "</body></html>";

        helper.setText(htmlContent, true);
        helper.setTo(model.getEmail());
        helper.setSubject("ASSIM'e Hoş Geldiniz, " + model.getName() + "!");

        javaMailSender.send(mimeMessage);
    }

    public void sendInfoConfirmManager(String email) throws MessagingException {
        //String activationLink = verifyAccountLink(email);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");


        String htmlContent = "<html><body>" +
                "<table cellpadding=\"0\" cellspacing=\"0\" align=\"center\" width=\"770px\" style=\"font-family:Arial,sans-serif;color:#000000;background-color:#f8f8f8;font-size:14px;\">" +
                "    <tbody><tr>" +
                "        <td style=\"padding-top:60px;padding-right:70px;padding-bottom:60px;padding-left:70px\">" +
                "            <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" style=\"border-color:#e6e6e6;border-width:1px;border-style:solid;background-color:#fff;padding-top:25px;padding-right:0;padding-bottom:50px;padding-left:30px\">" +
                "                <tbody><tr>" +
                "                    <td style=\"padding:0 30px 30px\">" +
                "                        <p style=\"font-family:Arial,sans-serif;color:#000000;font-size:19px;margin-top:14px;margin-bottom:0\">" +
                "                            Merhaba, " + "!" +
                "                        </p>" +

                "                        </p>" +
                "                        <ul style=\"font-family:Arial,sans-serif;color:#000000;font-size:14px;line-height:17px;margin-top:10px;margin-bottom:0\">" +
                "                            <li style=\"font-family:Arial,sans-serif;color:#000000;font-size:14px;line-height:17px;margin-top:0;margin-bottom:5px\">" +

                "                                <b>Hesabınız aktif edilmiştir,Sisteme giriş yapabilirsiniz  : </b>" +
                "                            </li>" +
                "                        </ul>" +
                "                    </td>" +
                "                </tr>" +
                "            </tbody></table>" +
                "            <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\">" +
                "                <tbody><tr>" +
                "                    <td style=\"padding-top:12px;\"></td>" +
                "                </tr>" +
                "                <tr>" +
                "                    <td style=\"font-family:Arial,sans-serif;font-size:12px;color:#888888;padding-right:30px;padding-left:30px\"> +</td>" +
                "                </tr>" +
                "      <img style=\"width: 300px;\" src =https://i.pinimg.com/736x/6e/ae/4a/6eae4a13af8db638a5e6bc344364646a.jpg />" +
                "            </tbody></table>" +
                "        </td>" +
                "    </tr></tbody></table>" +
                "</body></html>";

        helper.setText(htmlContent, true);
        helper.setTo(email);
        helper.setSubject("Aktivasyon HK.");

        javaMailSender.send(mimeMessage);
    }


    public String  changePassword() {
        return "http://localhost:9090/change-password";


    }

    public void sendPasswordResetEmail(EmployeeSendMailModel model) throws MessagingException {
        String resetLink = "http://localhost:9090/change-password";

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        String htmlContent = "<html><body>" +
                "<table cellpadding=\"0\" cellspacing=\"0\" align=\"center\" width=\"770px\" style=\"font-family:Arial,sans-serif;color:#000000;background-color:#f8f8f8;font-size:14px;\">" +
                "    <tbody><tr>" +
                "        <td style=\"padding-top:60px;padding-right:70px;padding-bottom:60px;padding-left:70px\">" +
                "            <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" style=\"border-color:#e6e6e6;border-width:1px;border-style:solid;background-color:#fff;padding-top:25px;padding-right:0;padding-bottom:50px;padding-left:30px\">" +
                "                <tbody><tr>" +
                "                    <td style=\"padding:0 30px 30px\">" +
                "                        <p style=\"font-family:Arial,sans-serif;color:#000000;font-size:19px;margin-top:14px;margin-bottom:0\">" +
                "                            Merhaba, " + model.getName() + "!" +
                "                        </p>" +
                "                        <ul style=\"font-family:Arial,sans-serif;color:#000000;font-size:14px;line-height:17px;margin-top:10px;margin-bottom:0\">" +
                "                            <li style=\"font-family:Arial,sans-serif;color:#000000;font-size:14px;line-height:17px;margin-top:0;margin-bottom:5px\">" +
                "                                <b>Şirket Adı: </b>" + model.getCompanyName() + "<br>" +
                "                                <b>Şifrenizi değiştirmek için lütfen aşağıdaki bağlantıya tıklayın: </b><a href=\"" + resetLink + "\">Şifre Yenileme Bağlantısı</a>" +
                "                            </li>" +
                "                        </ul>" +
                "                    </td>" +
                "                </tr>" +
                "            </tbody></table>" +
                "            <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\">" +
                "                <tbody><tr>" +
                "                    <td style=\"padding-top:12px;\"></td>" +
                "                </tr>" +
                "                <tr>" +
                "                    <td style=\"font-family:Arial,sans-serif;font-size:12px;color:#888888;padding-right:30px;padding-left:30px\"> +</td>" +
                "                </tr>" +
                "      <img style=\"width: 300px;\" src =https://i.pinimg.com/736x/6e/ae/4a/6eae4a13af8db638a5e6bc344364646a.jpg />" +
                "            </tbody></table>" +
                "        </td>" +
                "    </tr></tbody></table>" +
                "</body></html>";

        helper.setText(htmlContent, true);
        helper.setTo(model.getEmail());
        helper.setSubject("Şifrenizi Yenileyin, " + model.getName() + "!");

        javaMailSender.send(mimeMessage);
    }


    public String verifyAccountLink(String email) {
        return "http://localhost:19090/api/v1/auth/verifyEmail?email=" + email;
    }

    public void verifyAccount(String email) throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");


        String htmlContent = "<html><body>" +
                "<table cellpadding=\"0\" cellspacing=\"0\" align=\"center\" width=\"770px\" style=\"font-family:Arial,sans-serif;color:#000000;background-color:#f8f8f8;font-size:14px;\">" +
                "    <tbody><tr>" +
                "        <td style=\"padding-top:60px;padding-right:70px;padding-bottom:60px;padding-left:70px\">" +
                "            <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" style=\"border-color:#e6e6e6;border-width:1px;border-style:solid;background-color:#fff;padding-top:25px;padding-right:0;padding-bottom:50px;padding-left:30px\">" +
                "                <tbody><tr>" +
                "                    <td style=\"padding:0 30px 30px\">" +
                "                        <p style=\"font-family:Arial,sans-serif;color:#000000;font-size:19px;margin-top:14px;margin-bottom:0\">" +
                "                        </p>" +
                "                        <p style=\"font-family:Arial,sans-serif;color:#000000;font-size:14px;line-height:17px;margin-top:30px;margin-bottom:0\">" +
                "                            Hesabınız aktif edildi. " +
                "                        </p>" +
                "                        <ul style=\"font-family:Arial,sans-serif;color:#000000;font-size:14px;line-height:17px;margin-top:10px;margin-bottom:0\">" +
                "                            <li style=\"font-family:Arial,sans-serif;color:#000000;font-size:14px;line-height:17px;margin-top:0;margin-bottom:5px\">" +

                "                            </li>" +
                "                        </ul>" +
                "                    </td>" +
                "                </tr>" +
                "            </tbody></table>" +
                "            <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\">" +
                "                <tbody><tr>" +
                "                    <td style=\"padding-top:12px;\"></td>" +
                "                </tr>" +
                "                <tr>" +
                "                    <td style=\"font-family:Arial,sans-serif;font-size:12px;color:#888888;padding-right:30px;padding-left:30px\"> +</td>" +
                "                </tr>" +
                "      <img style=\"width: 300px;\" src =https://media.licdn.com/dms/image/C560BAQG6YMo64tCAYA/company-logo_200_200/0/1631433952841/linkedinik_logo?e=2147483647&v=beta&t=-TEZ-pxfUxLqkeZCsjdAG_jFm-YSel8YZuqdJujSHX0 />" +
                "            </tbody></table>" +
                "        </td>" +
                "    </tr></tbody></table>" +
                "</body></html>";

        helper.setText(htmlContent, true);
        helper.setTo(email);
        javaMailSender.send(mimeMessage);
    }


}
