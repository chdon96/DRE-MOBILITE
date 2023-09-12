package com.example.chadi.Email;

import com.example.chadi.Service.CandidatureService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
@AllArgsConstructor
@Slf4j
public class EmailService {
@Autowired
CandidatureService candidatureService;
    private final static Logger LOGGER = LoggerFactory
            .getLogger(EmailService.class);

    private final JavaMailSender mailSender;


    public void sendResetPass(String recipientEmail, String link)
            throws MessagingException, UnsupportedEncodingException {

        SimpleMailMessage message = new SimpleMailMessage();


        message.setFrom("youth.camp.store@gmail.com");
        message.setTo(recipientEmail);

        String subject = "Here's the link to reset your password";

        String content = "<p>Hello,</p>"
                + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>"
                + "<p><a href=\"" + link + "\">Change my password</a></p>"
                + "<br>"
                + "<p>Ignore this email if you do remember your password, "
                + "or you have not made the request.</p>";

        message.setSubject(subject);

        message.setText(content);

        mailSender.send(message);
    }


    public void sendEmailConfirm(String toEmail, String link){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Mobilité Internationale - Reset Password");
        message.setText(link);
        mailSender.send(message);


    }



//    public void yisendi(String to, String link) throws MessagingException {
//        MimeMessage message = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//        helper.setTo(to);
//        helper.setSubject("thank you for signing up");
//        helper.setText("<h1>Hello!</h1><p>This is a test email sent from Spring Boot.</p>", true);
//        mailSender.send(message);
//    }
@Async
public void send(String to, String email) {
    try {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper =
                new MimeMessageHelper(mimeMessage, "utf-8");
        helper.setText(email, true);
        helper.setTo(to);
        helper.setSubject("Confirm your email");
        helper.setFrom("hrizi.mohamedali@esprit.tn");
        mailSender.send(mimeMessage);
    } catch (MessagingException e) {
        LOGGER.error("failed to send email", e);
        throw new IllegalStateException("failed to send email");
    }
}

}
